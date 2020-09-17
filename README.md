# 介绍
    civism-sso基于springmvc+redis+shiro 实现的分布式单点登录系统,继承了登录验证以及数据接口鉴权，
    能很好的帮助其他项目做前后端分析，并且不需要其他子系统关心权限，并且该项目支持跨域请求
# 功能介绍
    * sso登录：统一登录后，跳转任何子系统不需要再次登录
    * 鉴权：在子项目中，filter拦截所有数据请求转发到sso的/authorize路径，根据sso返回值来判断是否有权限访问
    * 跨域：sso支持不同域名的访问，采用了jsonp请求方式，返回可执行代码来达到跨域
    * 前后端分离：sso只有json接口返回，没有页面，跳转逻辑完全由前端控制，能更好的促进前后端分离开发
# 操作步骤
    1. 修改redis配置文件
    2. 用jetty或tomcat启动civism-sso项目
    3. 访问localhost:9999/login/index?userName=admin&password=123456&way=1&callback=hello获取token
    4. 获取子系统菜单：localhost:9999/login/getMenus?hostName=www.baidu.com&callback=getMenus
    5. 鉴权：localhost:9999/authorize?url=/civism/index.html会返回是否有权限
# 说明
    * 除了以/login开头的不需要带token，其他的接口都需要把登录返回的token放到请求头中
    * 由于sso支持多个子系统，所有获取菜单的时候需要带域名，在数据库层次需要制定那些域名返回那些菜单，可以与权限集成，不同权限返回不同的菜单
    * 鉴权接口：该接口只验证了该用户在该角色的数据功能接口，所以设计数据库建议把菜单跟功能分离
# 使用手册
   [使用说明](https://github.com/civism/civism-sso/wiki)
# 具体接入介绍
  https://www.jianshu.com/p/1603c60f1de6
