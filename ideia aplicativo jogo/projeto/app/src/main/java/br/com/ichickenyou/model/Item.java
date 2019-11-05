package br.com.ichickenyou.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

//@Entity(tableName = "resultados")

public class Item {

    @PrimaryKey
    @NonNull private long id_resultado;
    private String campo_resultado_salvo;
    private Date data_de_criacao;
    private Boolean origem;
    private Boolean compartilhado;

}
