package org.carly.shared.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DataLoader {

    public static <T> void loadData(MongoRepository repository, String path, Class<T> type) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        InputStream inputStream = TypeReference.class.getResourceAsStream(path);
        JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, type);
        List<T> products = mapper.readValue(inputStream, javaType);
        repository.deleteAll();
        repository.saveAll(products);
    }
}
