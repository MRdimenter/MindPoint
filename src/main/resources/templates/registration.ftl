<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>

<h1 align="center">Регистрация</h1>

${message!}
    <div align="center">
        ${message?if_exists}
        <@l.login "/registration"  true/>
    </div>

</@c.page>