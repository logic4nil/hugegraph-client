package com.baidu.hugegraph.driver;

import java.util.List;
import java.util.Map;

import com.baidu.hugegraph.api.space.SchemaTemplateAPI;
import com.baidu.hugegraph.client.RestClient;
import com.baidu.hugegraph.structure.space.SchemaTemplate;


public class SchemaTemplateManager {
    private SchemaTemplateAPI schemaTemplateAPI;
    public SchemaTemplateManager(RestClient client, String graphSpace) {
        this.schemaTemplateAPI = new SchemaTemplateAPI(client, graphSpace);
    }

    public List<String> listSchemTemplate() {
        return this.schemaTemplateAPI.list();
    }

    public Map getSchemaTemplate(String name) {
        return this.schemaTemplateAPI.get(name);
    }

    public Map createSchemaTemplate(SchemaTemplate template) {
        SchemaTemplate.SchemaTemplateReq req
                = SchemaTemplate.SchemaTemplateReq.fromBase(template);
        return this.schemaTemplateAPI.create(req);
    }

    public Map updateSchemaTemplate(SchemaTemplate template) {
        SchemaTemplate.SchemaTemplateReq req
                = SchemaTemplate.SchemaTemplateReq.fromBase(template);

        return this.schemaTemplateAPI.update(req);
    }

    public void deleteSchemaTemplate(String name) {
        this.schemaTemplateAPI.delete(name);
    }
}
