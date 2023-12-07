package io.github.xxmd;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HistoryUtil {
    public static final String PREFERENCE_FILE_KEY = "PREFERENCE_FILE_KEY";

    public static <T> List<T> getList(Context context, String preferenceKey, Class<T> clazz) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(preferenceKey, "");
        if (StringUtils.isEmpty(json)) {
            return new ArrayList<>();
        } else {
            Gson gson = new Gson();
            Type type = TypeToken.getParameterized(List.class, clazz).getType();
            List<T> list = gson.fromJson(json, type);
            return list;
        }
    }

    public static void setList(Context context, String preferenceKey, List list) {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(preferenceKey, gson.toJson(list));
    }

    public static <T> void remove(Context context, String preferenceKey, Class<T> clazz, T item) {
        List<T> list = getList(context, preferenceKey, clazz);
        list.remove(item);
        setList(context, preferenceKey, list);
    }

    public static <T> void removeAll(Context context, String preferenceKey, Class<T> clazz, List<T> list) {
        List<T> srcList = getList(context, preferenceKey, clazz);
        srcList.removeAll(list);
        setList(context, preferenceKey, srcList);
    }

    public static void removeAll(Context context, String preferenceKey) {
        setList(context, preferenceKey, new ArrayList());
    }

    public static <T> void prepend(Context context, String preferenceKey, Class<T> clazz, T item) {
        List<T> srcList = getList(context, preferenceKey, clazz);
        srcList.contains(item);
        srcList.remove(item);
        srcList.add(0, item);
        setList(context, preferenceKey, srcList);
    }

    public static <T> void prependAll(Context context, String preferenceKey, Class<T> clazz, List<T> prependList) {
        List<T> srcList = getList(context, preferenceKey, clazz);
        for (T item : prependList) {
            if (srcList.contains(item)) {
                srcList.remove(item);
            }
        }
        srcList.addAll(0, prependList);
        setList(context, preferenceKey, srcList);
    }
}
