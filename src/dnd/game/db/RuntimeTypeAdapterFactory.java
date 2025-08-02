package dnd.game.db;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RuntimeTypeAdapterFactory<T> implements TypeAdapterFactory {
    private final Class<T> baseType;
    private final String typeFieldName;
    private final Map<String, Class<? extends T>> labelToSubtype = new HashMap<>();
    private final Gson delegateGson = new Gson();

    public static <T> RuntimeTypeAdapterFactory<T> of(Class<T> baseType, String typeFieldName) {
        return new RuntimeTypeAdapterFactory<>(baseType, typeFieldName);
    }

    private RuntimeTypeAdapterFactory(Class<T> baseType, String typeFieldName) {
        this.baseType = baseType;
        this.typeFieldName = typeFieldName;
    }

    public RuntimeTypeAdapterFactory<T> registerSubtype(Class<? extends T> subtype, String label) {
        labelToSubtype.put(label, subtype);
        return this;
    }

    @Override
    public <R> TypeAdapter<R> create(Gson gson, TypeToken<R> type) {
        if (!baseType.isAssignableFrom(type.getRawType())) return null;

        return new TypeAdapter<R>() {
            @Override
            public void write(JsonWriter out, R value) throws IOException {
                Class<?> srcType = value.getClass();
                String label = labelToSubtype.entrySet().stream()
                        .filter(e -> e.getValue().equals(srcType))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElseThrow(() -> new JsonParseException("Unknown subtype: " + srcType));

                JsonObject jsonObject = gson.toJsonTree(value).getAsJsonObject();
                jsonObject.addProperty(typeFieldName, label);
                gson.toJson(jsonObject, out);
            }

            @Override
            public R read(JsonReader in) throws IOException {
                JsonObject jsonObject = JsonParser.parseReader(in).getAsJsonObject();
                JsonElement labelElem = jsonObject.get(typeFieldName);
                if (labelElem == null) throw new JsonParseException("Missing type field: " + typeFieldName);

                String label = labelElem.getAsString();
                Class<? extends T> subtype = labelToSubtype.get(label);
                if (subtype == null) throw new JsonParseException("Unknown label: " + label);

                return (R) delegateGson.fromJson(jsonObject, subtype);
            }
        };
    }
}

