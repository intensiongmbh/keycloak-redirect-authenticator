<#import "template.ftl" as layout>
<@layout.registrationLayout displayMessage=false; section>
    <#if section = "header">
        <h1>Success</h1>
    <#elseif section = "form">
You will be redirected to "${redirectUri}" in 5 seconds
  <script>
            (function () {
                setTimeout(function() {
                  window.location.replace('${redirectUri}');
                }, 5000);
            })();
        </script>
    </#if>
</@layout.registrationLayout>