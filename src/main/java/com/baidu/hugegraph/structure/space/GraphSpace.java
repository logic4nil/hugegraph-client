package com.baidu.hugegraph.structure.space;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class GraphSpace {

    @JsonProperty("name")
    private String name;
    @JsonProperty("description")

    private String description;
    @JsonProperty("cpu_limit")
    private int cpuLimit;
    @JsonProperty("memory_limit")
    private int memoryLimit; // GB
    @JsonProperty("storage_limit")
    public int storageLimit; // GB
    @JsonProperty("max_graph_number")
    private int maxGraphNumber;
    @JsonProperty("max_role_number")
    private int maxRoleNumber;

    @JsonProperty("oltp_namespace")
    public String oltpNamespace;
    @JsonProperty("olap_namespace")
    private String olapNamespace;
    @JsonProperty("storage_namespace")
    private String storageNamespace;


    @JsonProperty("cpu_used")
    private int cpuUsed;
    @JsonProperty("memory_used")
    private int memoryUsed;
    @JsonProperty("storage_used")
    private int storageUsed;
    @JsonProperty("graph_number_used")
    private int graphNumberUsed;
    @JsonProperty("role_number_used")
    private int roleNumberUsed;

    public GraphSpace() {
    }

    public GraphSpace(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public GraphSpace setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GraphSpace setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getCpuLimit() {
        return cpuLimit;
    }

    public GraphSpace setCpuLimit(int cpuLimit) {
        this.cpuLimit = cpuLimit;
        return this;
    }

    public int getMemoryLimit() {
        return memoryLimit;
    }

    public GraphSpace setMemoryLimit(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        return this;
    }

    public int getStorageLimit() {
        return storageLimit;
    }

    public GraphSpace setStorageLimit(int storageLimit) {
        this.storageLimit = storageLimit;
        return this;
    }

    public String getOltpNamespace() {
        return oltpNamespace;
    }

    public GraphSpace setOltpNamespace(String oltpNamespace) {
        this.oltpNamespace = oltpNamespace;
        return this;
    }

    public String getOlapNamespace() {
        return olapNamespace;
    }

    public GraphSpace setOlapNamespace(String olapNamespace) {
        this.olapNamespace = olapNamespace;
        return this;
    }

    public String getStorageNamespace() {
        return storageNamespace;
    }

    public GraphSpace setStorageNamespace(String storageNamespace) {
        this.storageNamespace = storageNamespace;
        return this;
    }

    public int getMaxGraphNumber() {
        return maxGraphNumber;
    }

    public GraphSpace setMaxGraphNumber(int maxGraphNumber) {
        this.maxGraphNumber = maxGraphNumber;
        return this;
    }

    public int getMaxRoleNumber() {
        return maxRoleNumber;
    }

    public GraphSpace setMaxRoleNumber(int maxRoleNumber) {
        this.maxRoleNumber = maxRoleNumber;
        return this;
    }

    public int getCpuUsed() {
        return cpuUsed;
    }

    public GraphSpace setCpuUsed(int cpuUsed) {
        this.cpuUsed = cpuUsed;
        return this;
    }

    public int getMemoryUsed() {
        return memoryUsed;
    }

    public GraphSpace setMemoryUsed(int memoryUsed) {
        this.memoryUsed = memoryUsed;
        return this;
    }

    public int getStorageUsed() {
        return storageUsed;
    }

    public GraphSpace setStorageUsed(int storageUsed) {
        this.storageUsed = storageUsed;
        return this;
    }

    public int getGraphNumberUsed() {
        return graphNumberUsed;
    }

    public GraphSpace setGraphNumberUsed(int graphNumberUsed) {
        this.graphNumberUsed = graphNumberUsed;
        return this;
    }

    public int getRoleNumberUsed() {
        return roleNumberUsed;
    }

    public GraphSpace setRoleNumberUsed(int roleNumberUsed) {
        this.roleNumberUsed = roleNumberUsed;
        return this;
    }
}