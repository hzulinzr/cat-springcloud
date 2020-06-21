package com.lin.feign;

import com.lin.fallback.BookServiceFallback;
import com.lin.response.Wrapper;
import com.lin.vo.BookUrlVo;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lzr
 * @date 2020-04-01 22:41:25
 */
@FeignClient(name = "cat-provider-book")
public interface FileServiceFeign {
    /**
     * 上传书籍
     * @param file 路径
     * @return
     */
    @PostMapping(value = "/book/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Wrapper<BookUrlVo> bookUpload(@RequestPart("file") MultipartFile file);

//    @Configuration
//    class MultipartSupportConfig {
//        @Bean
//        public Encoder feignFormEncoder() {
//            return new SpringFormEncoder();
//        }
//    }
}
