# jpaproject

Heroku deployment

1.메이븐 설정
 <properties>
        <spring-version>5.0.4.RELEASE</spring-version>
        <hibernate-version>5.2.15.Final</hibernate-version>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <version>3.0.2</version>
                <configuration>
                    <webXml>src\main\webapp\WEB-INF\web.xml</webXml>
                    <target>1.8</target>
                    <source>1.8</source>
                </configuration>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals><goal>copy</goal></goals>
                        <configuration>
                            <artifactItems>
                                <artifactItem>
                                    <groupId>com.github.jsimone</groupId>
                                    <artifactId>webapp-runner</artifactId>
                                    <version>8.5.27.0</version>
                                    <destFileName>webapp-runner.jar</destFileName>
                                </artifactItem>
                            </artifactItems>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
        <finalName>helloWorld</finalName>
    </build>


2.의존 추가
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>9.4-1206-jdbc42</version>
        </dependency>

3.root 폴더에 Procfile 생성
web: java $JAVA_OPTS -jar target/dependency/webapp-runner.jar --port $PORT target/*.war

maven build 후  


root 폴더로 이동
커맨드창
git init
git add .
git commit -m "message"
heroku create "prejectname"
git push heroku master
heroku open
heroku addons:create heroku-postgresql


heroku ps:scale web=1

    <bean class="java.net.URI" id="dbUrl">
        <constructor-arg value="#{systemEnvironment['DATABASE_URL']}"/>
    </bean>

	 커넥션 생성
   <!--connection-pool datasource -->
    <bean id="dataSource" class="org.apache.tomcat.jdbc.pool.DataSource"
          p:driverClassName="org.postgresql.Driver">
        <property name="url" value="#{ 'jdbc:postgresql://' + @dbUrl.getHost() + ':' + @dbUrl.getPort() + @dbUrl.getPath() }"/>
        <property name="username" value="#{ @dbUrl.getUserInfo().split(':')[0] }"/>
        <property name="password" value="#{ @dbUrl.getUserInfo().split(':')[1] }"/>
    </bean>

