
# 專案架構


```
├─src/
│  ├─main/
│  │  ├─java/
│  │  │  └─com.demo.crm.crm_java_demo/
│  │  │     ├─config                        系統設定層
│  │  │     ├─constant                      常數層
│  │  │     ├─controller                    API層
│  │  │     ├─entity                        資料庫物件實體層
│  │  │     ├─exception                     客製化錯誤訊息
│  │  │     ├─filter                        filter層
│  │  │     ├─repository                    儲存層
│  │  │     ├─req                           API請求實體層
│  │  │     ├─res                           API回傳實體層
│  │  │     ├─service                       服務層
│  │  │     ├─utils                         共用方法
│  │  │     └─CrmJavaDemoApplication.java   主程式
│  │  └─resources/
│  │     ├─application.properties   系統設定
│  │     ├─data.sql                 H2-DB假資料
│  │     └─schedma.sql              H2-DB設定檔
│  └─test/
├─pom.xml
└─README.md
```


# 環境
* java 17

# 測試號
| 系統權限        | username | password |
|-------------|----------|----------|
| SUPPER_USER | user1    | user1    |
| MANAGER     | user2    | user2    |
| OPERATOR    | user3    | user3    |
# 遠端測試
* [AWS 上的 swagger 文件](http://52.195.209.124:12345/swagger-ui/index.html#/)

    
- AWS S3
  - 將jar檔存放至S3
  - 建立IAM user 可以存取S3權限

- AWS lightsail 執行個體(Debian)
  - SSH連接
    ```
    sudo apt install openjdk-17-jdk
    sudo apt-get install awscli
    aws configure
    aws s3 cp s3://[your-bucket-name]/[your-jar-file].jar .
    java -jar CRM_java_demo-0.0.1-SNAPSHOT.jar
    ```
  - 開啟防火牆
    - AWS Lightsail Web > 該執行個體 > 聯網 > IPV4防火牆 > 新增規則 > 協定:TCP 連接埠:12345 > 儲存 

# Local測試

* 導航到项目根目录後再執行以下代碼
```shell
# 清理並構建項目，跳過测試
mvn clean install 
# 執行程式
mvn spring-boot:run
```

* 若不行就找到JAR檔
```
# 運行生成的JAR文件
java -jar /target/CRM_java_demo-0.0.1-SNAPSHOT.jar
```

* [Local 的 Swagger 文件](http://localhost:12345/swagger-ui/index.html)






