<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-group">
        <form method="get" action="/main" class="form-inline">
            <input class="form-control" type="text" name="filter" value="${filter?if_exists}">
            <button class="btn btn-dark btn-sm ml-2" type="submit">Найти</button>
        </form>
    </div>

    <a class="btn btn-primary btn-sm" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Написать сообщение
    </a>
    <div class="collapse mt-2" id="collapseExample">
        <div class="form-inline">
            <form method="post" enctype="multipart/form-data">
                <div class="form-inline">
                    <input class="form-control" type="text" name="text" placeholder="Введите сообщение"/>
                </div>
                <div class="form-inline">
                    <input class="form-control" type="text" name="tag" placeholder="Введите тег"/>
                </div>
                <div class="custom-file">
                    <input class="custom-file-input" id="customFile" type="file" name="file">
                    <label class="custom-file-label" for="customFile">Выберите файл</label>
                </div>
                <button class="btn btn-dark btn-sm mt-2 " type="submit">Добавить</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
            </form>
        </div>
    </div>




    <div class="card-columns">
        <#list messages as message> <!-- Цикл обхода списка во фримаркире -->
            <div class=" card bg-light my-3" style="width: 19rem;">
                <div class="card-img-top">
                    <#if message.filename??>
                        <img width="302px" heigth="300px" src="/img/${message.filename}">
                    </#if>
                </div>
                <div class="m-2">
                    <span>${message.text}</span>
                    <i class="text-primary">${message.tag}</i>
                </div>
                <div class="card-footer text-muted">
                    ${message.authorName}
                </div>


            </div>
    <#else>
        <span>No messages</span>
        </#list>
    </div>
</@c.page>