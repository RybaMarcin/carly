package org.carly.support;

import org.bson.types.ObjectId;
import org.carly.core.partsmanagement.model.entity.Windows;

import static org.carly.core.shared.utils.builder.Builder.anObject;

public class WindowsModel {

    public static final ObjectId WINDOWS_ID_1 = new ObjectId("5fc1cd86be0ad871841e9084");
    public static final String WINDOWS_NAME_1 = "Darkness";
    public static final String WINDOWS_COLOR_1 = "Grey";

    public static Windows aWindows1() {
        return anObject(Windows.class)
                .with(w -> w.setId(WINDOWS_ID_1))
                .with(w -> w.setName(WINDOWS_NAME_1))
                .with(w -> w.setColor(WINDOWS_COLOR_1))
                .build();
    }
}
