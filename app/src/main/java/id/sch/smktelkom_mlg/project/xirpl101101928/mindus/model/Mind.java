package id.sch.smktelkom_mlg.project.xirpl101101928.mindus.model;

/**
 * Created by A455L on 17/11/2016.
 */

import java.io.Serializable;

public class Mind implements Serializable {
    public String task;
    public String deskripsi;
    public String detail;
    public String due;
    public String foto;

    public Mind(String task, String deskripsi, String detail, String due, String foto) {
        this.task = task;
        this.deskripsi = deskripsi;
        this.detail = detail;
        this.due = due;
        this.foto = foto;
    }
}