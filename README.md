# springboot异常处理
- 在springboot启动器加入＠EnableTransactional开启事物管理
- 使用druid作为连接池，配置ServletRegistrationBean和 FilterRegistrationBean 
- 利用声明式事物,在方法上加入注解＠Transactional,对于异常操作进行回滚
- 如果需要对异常进行手动处理，则需要手动回滚事物。
  - TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();不过需要注意的是发生异常需要第一时间进行手动回滚事物，也就是要在异常抛出之前
-如果我们在使用事物 @Transactional 的时候，调用了其他的子方法进行了数据库的操作，
  - 但是我们想使其事物生效的话，我们可以使用rollbackFor注解或者将该子方法的异常抛出由调用的方法进行处理，不过这里需要注意的是，子方法也必须是公共的方法
  
