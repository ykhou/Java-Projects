/**
* @author kangkang
*/
public class ${className}DO {
    <#list files as item>
        <#if item.comment??>
            /**
            * ${item.comment}
            */
        </#if>
        private ${item.type} ${item.fileName?uncap_first};
    </#list>

    <#list files as item>
        public ${item.type} get${item.fileName}() {
            return this.${item.fileName?uncap_first};
        }

        public void set${item.fileName}(${item.type} ${item.fileName?uncap_first}) {
            this.${item.fileName?uncap_first} = ${item.fileName?uncap_first};
        }
    </#list>
}