<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<div>
    <form method="post">
        <input type="text" name="text" placeholder="Введите сообщение"/>
        <input type="text" name="tag" placeholder="Введите тег"/>
        <button type="submit">Добавить</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</div>

<div>Список сообщений </div>

<form method="get" action="/main">
    <input type="text" name="filter" value="${filter?if_exists}">
    <button type="submit">Найти</button>
</form>

<#list messages as message> <!-- Цикл обхода списка во фримаркире -->
    <div>
        <b>${message.id}</b>
        <span>${message.text}</span>
        <i>${message.tag}</i>
        <strong>${message.authorName}</strong>
    </div>
    <#else>
    <span>No messages</span>
</#list>

<div>
<@l.logout/>
</div>
</@c.page>