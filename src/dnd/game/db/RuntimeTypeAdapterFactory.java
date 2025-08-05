package dnd.game.db;

import com.google.gson.*;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RuntimeTypeAdapterFactory<T> implements TypeAdapterFactory {
    private final Class<T> baseType;
    private final String typeFieldName;
    private final Map<String, Class<? extends T>> labelToSubtype = new HashMap<>();
    private final Map<Class<? extends T>, String> subtypeToLabel = new HashMap<>();

    public static <T> RuntimeTypeAdapterFactory<T> of(Class<T> baseType, String typeFieldName) {
        return new RuntimeTypeAdapterFactory<>(baseType, typeFieldName);
    }

    private RuntimeTypeAdapterFactory(Class<T> baseType, String typeFieldName) {
        this.baseType = baseType;
        this.typeFieldName = typeFieldName;
    }

    public RuntimeTypeAdapterFactory<T> registerSubtype(Class<? extends T> subtype, String label) {
        labelToSubtype.put(label, subtype);
        subtypeToLabel.put(subtype, label);
        return this;
    }

    @Override
    public <R> TypeAdapter<R> create(Gson gson, TypeToken<R> type) {
        if (!baseType.isAssignableFrom(type.getRawType())) return null;

        Map<String, TypeAdapter<?>> labelToAdapter = new HashMap<>();
        Map<Class<?>, TypeAdapter<?>> subtypeToAdapter = new HashMap<>();

        for (Map.Entry<String, Class<? extends T>> entry : labelToSubtype.entrySet()) {
            TypeAdapter<?> delegate = gson.getDelegateAdapter(this, TypeToken.get(entry.getValue()));
            labelToAdapter.put(entry.getKey(), delegate);
            subtypeToAdapter.put(entry.getValue(), delegate);
        }

        return new TypeAdapter<R>() {
            @Override
            public void write(JsonWriter out, R value) throws IOException {
                Class<?> srcType = value.getClass();
                @SuppressWarnings("unchecked")
                String label = subtypeToLabel.get((Class<? extends T>) srcType);

                if (label == null) {
                    throw new JsonParseException("Unknown subtype: " + srcType);
                }

                @SuppressWarnings("unchecked")
                TypeAdapter<R> delegate = (TypeAdapter<R>) subtypeToAdapter.get(srcType);
                JsonObject jsonObject = delegate.toJsonTree(value).getAsJsonObject();
                jsonObject.addProperty(typeFieldName, label);
                Streams.write(jsonObject, out);
            }

            @Override
            public R read(JsonReader in) throws IOException {
                JsonElement element = Streams.parse(in);
                JsonObject jsonObject = element.getAsJsonObject();

                JsonElement labelElement = jsonObject.get(typeFieldName);
                if (labelElement == null) {
                    throw new JsonParseException("Missing type field: " + typeFieldName);
                }

                String label = labelElement.getAsString();
                @SuppressWarnings("unchecked")
                TypeAdapter<R> delegate = (TypeAdapter<R>) labelToAdapter.get(label);
                if (delegate == null) {
                    throw new JsonParseException("Unknown label: " + label);
                }

                return delegate.fromJsonTree(jsonObject);
            }
        }.nullSafe(); // makes it handle null values gracefully
    }
}