package com.bear

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration
import org.springframework.boot.context.embedded.ServletRegistrationBean
import org.springframework.context.annotation.{Bean, ImportResource}
import org.springframework.web.servlet.DispatcherServlet

/**
  * Created by Apple on 16/6/2.
  */
@SpringBootApplication(exclude= Array(classOf[DataSourceAutoConfiguration], classOf[MongoDataAutoConfiguration]))
//@PropertySource(Array("classpath:properties/application.properties"))
@ImportResource(Array("classpath:spring/spring-context.xml"))
class Application{
  @Bean
  def dispatchServlet: DispatcherServlet = {
    new DispatcherServlet()
  }

  @Bean
  def dispatcherServletRegistration: ServletRegistrationBean = {
    val srt = new ServletRegistrationBean(dispatchServlet, "/bear/*")
    srt.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME)
    srt
  }
}

object AppMain{
  def main(args: Array[String]) {
    SpringApplication.run(classOf[Application])
  }
}