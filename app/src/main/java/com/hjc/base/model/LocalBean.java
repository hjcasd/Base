package com.hjc.base.model;

import com.contrarywind.interfaces.IPickerViewData;
import java.util.List;

public class LocalBean implements IPickerViewData {
    private String name;
    private List<String> city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCity() {
        return city;
    }

    public void setCity(List<String> city) {
        this.city = city;
    }

    @Override
    public String getPickerViewText() {
        return this.name;
    }
}
