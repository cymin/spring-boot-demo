1. 登录
    
    
    curl -H "Content-Type: application/json" -X POST -d '{"username":"chen","password":123}' http://localhost:8080/login


返回：
    
    {"id":1,"name":"chen","roles":[{"id":2,"name":"guest","permissions":[{"id":1,"name":"read"}]},{"id":1,"name":"admin","permissions":[{"id":2,"name":"write"},{"id":1,"name":"read"}]}]}






