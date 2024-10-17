package config.spring.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

public class TransactionalConfig {
	
	private static final String CUSTOMIZE_TRANSACTION_INTERCEPTOR_NAME = "customizeTransactionInterceptor";
	
	private static final String[] DEFAULT_TRANSACTION_BEAN_NAMES = {"*Service", "*ServiceImpl"};
	
	private static final String[] DEFAULT_READ_ONLY_METHOD_RULE_TRANSACTION_ATTRIBUTES = {
		"get*",
		"count*",
		"find*",
		"list*",
		"*"
	};
	
	private static final String[] DEFAULT_REQUIRED_METHOD_RULE_TRANSACTION_ATTRIBUTES = {
		"add*",
		"save*",
		"modify*",
		"edit*",
		"remove*",
		"process*"
	};
	
	@Bean( CUSTOMIZE_TRANSACTION_INTERCEPTOR_NAME )
	public TransactionInterceptor customizeTransactionInterceptor (PlatformTransactionManager transactionManager) {
		NameMatchTransactionAttributeSource transactionAttributeSource = new NameMatchTransactionAttributeSource();
		RuleBasedTransactionAttribute readOnly = this.readOnlyTransactionRule();
		RuleBasedTransactionAttribute required = this.requiredTransactionRule();
		
		for (String methodName : DEFAULT_READ_ONLY_METHOD_RULE_TRANSACTION_ATTRIBUTES) {
			transactionAttributeSource.addTransactionalMethod(methodName , readOnly);
		}
		
		for (String methodName : DEFAULT_REQUIRED_METHOD_RULE_TRANSACTION_ATTRIBUTES) {
			transactionAttributeSource.addTransactionalMethod(methodName , required);
		}
		return new TransactionInterceptor(transactionManager, transactionAttributeSource);
	}
	
	@Bean
	public BeanNameAutoProxyCreator customizeTransactionBeanNameAutoProxyCreator () {
		BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
		
		beanNameAutoProxyCreator.setInterceptorNames(CUSTOMIZE_TRANSACTION_INTERCEPTOR_NAME);
		List<String> transactionBeanNames = new ArrayList<>(DEFAULT_TRANSACTION_BEAN_NAMES.length);
		
		transactionBeanNames.addAll(Arrays.asList(DEFAULT_TRANSACTION_BEAN_NAMES));
		
		for (String transactionBeanName : transactionBeanNames) {
			beanNameAutoProxyCreator.setBeanNames(transactionBeanName);
		}
		beanNameAutoProxyCreator.setProxyTargetClass(true);
		return beanNameAutoProxyCreator;
	}
	
	private RuleBasedTransactionAttribute readOnlyTransactionRule () {
		RuleBasedTransactionAttribute readOnly = new RuleBasedTransactionAttribute();
		readOnly.setReadOnly(true);
		readOnly.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
		return readOnly;
	}
	
	private RuleBasedTransactionAttribute requiredTransactionRule () {
		RuleBasedTransactionAttribute required = new RuleBasedTransactionAttribute();
		required.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		required.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		required.setTimeout(TransactionDefinition.TIMEOUT_DEFAULT);
		return required;
	}

}