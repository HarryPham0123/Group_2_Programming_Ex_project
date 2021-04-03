package com.surveyapp.service.dao;

import lombok.NonNull;


public class InsertQuestionnaireDAO extends ProcedureBaseDAO {
    private String procedureQuery = "{CALL %s('%s', '%s', '%s')}";
    private String procedureName;

    public InsertQuestionnaireDAO(@NonNull String procedureName) {
        this.procedureName = procedureName;
    }

    public InsertQuestionnaireDAO setParameters(@NonNull String Lcode,
                                                @NonNull String Ccode,
                                                @NonNull String answer
    ) {
        super.procedureQuery = String.format(this.procedureQuery, this.procedureName, Lcode, Ccode,answer);
        return this;
    }
}
