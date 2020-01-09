package com.lin.feignConfig;

/**
 * @author lzr
 * @date 2020-01-07 13:50:59
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * Feign请求拦截器
 * @author lzr
 */
@Slf4j
@Component
public class FeignCustomRequestInterceptor implements RequestInterceptor {

    private ObjectMapper objectMapper;

    public FeignCustomRequestInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Feign请求拦截应用
     * @param requestTemplate 请求模板
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // feign组件不支持GET方式POJO，需要转换格式
        if (HttpMethod.GET.name().equals(requestTemplate.method()) && null != requestTemplate.body()) {
            Map<String, Collection<String>> queries = new HashMap<>(16);
            try {
                JsonNode jsonNode = objectMapper.readTree(requestTemplate.body());
                requestTemplate.body(null);
                // 构建 Map
                buildQuery(jsonNode, "", queries);
            } catch (IOException e) {
                log.error("【FeignCustomRequestInterceptor】数据转换异常:" + e.getMessage(), e);
            }
            // queries 就是 POJO 解析为 Map 后的数据
            requestTemplate.queries(queries);
        }
    }

    /**
     * 构建查询数据
     * @param jsonNode 数据
     * @param path 路径参数
     * @param queries 请求参数容器
     */
    private void buildQuery(JsonNode jsonNode, String path, Map<String, Collection<String>> queries) {
        if (!jsonNode.isContainerNode()) {
            if (jsonNode.isNull()) {
                return;
            }
            Collection<String> pathValues = queries.computeIfAbsent(path, k -> new ArrayList<>());
            pathValues.add(jsonNode.asText());
            return;
        }
        if (jsonNode.isArray()) {
            Iterator<JsonNode> it = jsonNode.elements();
            while (it.hasNext()) {
                buildQuery(it.next(), path, queries);
            }
        } else {
            Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> entry = it.next();
                if (StringUtils.hasText(path)) {
                    buildQuery(entry.getValue(), path + "." + entry.getKey(), queries);
                } else {
                    // 根节点
                    buildQuery(entry.getValue(), entry.getKey(), queries);
                }
            }
        }
    }
}

