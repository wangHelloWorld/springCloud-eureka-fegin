# springCloud-eureka-fegin
springCloud-eureka-fegin

使用工具IDEA
1.创建springcolud-eureka服务：为组件注册服务器
  创建module使用IDEA自带的springInInilitilizer添加依赖使用eureka-server+Cloud security
  为了安全考虑增加spring security插件
  pom.xml文件如下
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.7.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.wrh.springcloud</groupId>
    <artifactId>project-eureka</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>project-eureka</name>
    <description>project-eureka for Spring Cloud EUREKA</description>

    <properties>
        <java.version>1.8</java.version>
        <spring-cloud.version>Greenwich.SR2</spring-cloud.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-security</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

application.yml配置如下
server:
  port: 7778
  servlet:
    context-path: /
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7778/eureka/
    fetch-registry: false
    register-with-eureka: false
spring:
  security:
    user:
      name: root
      password: 123456
 在主启动类中添加@EnableEurekaServer注解即可
 

2.创建服务端（服务端分为两个部分，一个是provider，一个是consumer）
  首先创建provider
  依旧使用IDEA中的springInInilitilizer创建，但使用的组件是eureka-discovery-client
  
  pom.xml文件如下：
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.7.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.wrh.springcloud</groupId>
    <artifactId>project-user-provider</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>project-user-provider</name>
    <description>project-user-provider for Spring Cloud</description>

    <properties>
        <java.version>1.8</java.version>
        <spring-cloud.version>Greenwich.SR2</spring-cloud.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

application.ymls文件如下：
server:
  port: 7779
eureka:
  client:
    service-url:
      defaultZone: http://root:123456@localhost:7778/eureka/
spring:
  application:
    name: project-user-provider
    
在启动类中添加@EnableDiscoveryClient注解即可

实际操作中遇到的问题如下：
1.eureka-server添加spring-security时，要在client中defaultZone中添加user:passwword
2.实际client连接不上server
  2.1 报错：Saw local status change event StatusChangeEvent： 
      在client端添加spring-boot-starter-web包
  2.2 报错：com.netflix.discovery.shared.transport.TransportException: Cannot execute request on any known server
      在server端添加一个配置类WebConfiguration extends WebSecurityConfigurerAdapter重写 
      protected  void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        super.configure(http);
      }该方法并在类上添加@EnableWebSecurity即可
      
      
  其次创建consumer
  依旧使用IDEA中的springInInilitilizer创建，但使用的组件是eureka-discovery-client，同样需要添加spring-boot-starter-web包
  在启动类中添加@@EnableDiscoveryClient注解,@EnableFeginClients注解
  
  添加interface：目的是映射provider中的服务，在interface上添加注解@FeiginClient(name="project-user-provider")name是注册到eureka server上的服务名称
  然后在interface中编写目标方法和provider中提供的方法一致即可。
  
  下面就是创建controller,正常编写，将interface自动注入即可直接使用。
  注意，spring-boot默认是扫面启动类包下的所有信息（添加了注解的自动装载）。
  启动测试，成功
 
 consumer的配置文件如下
  1 pom.xml
    <?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.7.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.wrh.springcloud</groupId>
    <artifactId>project-user-consumer</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>project-user-consumer</name>
    <description>project-user-consumer project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
        <spring-cloud.version>Greenwich.SR2</spring-cloud.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
2.application.yml
server:
  port: 8000
spring:
  application:
    name: project-user-consumer
eureka:
  client:
    service-url:
      defaultZone: http://root:123456@localhost:7778/eureka/


 在最后我使用common组件去完成共有参数的创建
 具体做法：
 创建新的Maven项目，通过Maven的install发布到本地的仓库，通过groupId，artifactId的pom信息，将common组件引入到需要的项目中如provider，conusmer
 
 在provider项目中使用@RequestBody接受来自consumer的访问参数。
 
 consumer中可以使用@QequestParam("")接收参数并组合成provider需要的对象。也可以直接使用@ReqeustBody，但使用postman测试的时候，需要使用body传参
