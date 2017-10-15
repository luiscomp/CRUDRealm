package com.example.admed.crudrealm.application;

import io.realm.DynamicRealm;
import io.realm.RealmSchema;

/**
 * Created by Gabriel Ângelo on 13/10/2017.
 */

public class RealmMigration implements io.realm.RealmMigration {

    public static final int VERSION = 3;

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        RealmSchema schema = realm.getSchema();

        if (oldVersion == 0) {
            schema.get("CursoVO").addRealmObjectField("professorVO", schema.get("ProfessorVO"));
            oldVersion++;
        }

        if (oldVersion == 1) {
            schema.get("CursoVO").addRealmListField("alunos", schema.get("AlunoVO"));
            oldVersion++;
        }
    }
}
