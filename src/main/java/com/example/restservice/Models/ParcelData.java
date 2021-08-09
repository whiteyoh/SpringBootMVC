package com.example.restservice.Models;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class ParcelData {
    private static String parcel_unit;
    public ParcelData(String parcel_unit) {
        this.parcel_unit = parcel_unit;
    }
}
