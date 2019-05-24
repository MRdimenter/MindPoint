<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>

<h1 align="center">Регистрация</h1>

${message!}

<@l.login "/registration"/>
</@c.page>