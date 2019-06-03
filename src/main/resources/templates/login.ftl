<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>


    <div align="center">
        ${message?if_exists}
    <@l.login "/login" false/>
    </div>




</@c.page>

