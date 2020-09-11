package com.eas.challenge.notes.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.view.ContextThemeWrapper;

import com.eas.challenge.R;
import com.eas.sdk.commons.models.Note;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class Details {

    public static void showDialog(Context context, Note note) {//NOSONAR
        final AlertDialog.Builder alert = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.AppTheme));
        final LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View view = layoutInflater.inflate(R.layout.dialog_note_details, null);
        alert.setCancelable(true);
        final ImageView image = view.findViewById(R.id.iv_poster);
        final TextView tvTitle = view.findViewById(R.id.tv_title);
        final TextView tvContent = view.findViewById(R.id.tv_content);
        final TextView seccion = view.findViewById(R.id.tv_section_note);
        final TextView tvdate = view.findViewById(R.id.tv_date);
        final TextView tvAutor = view.findViewById(R.id.tv_autor);
        tvTitle.setText(note.getTitle());
        tvContent.setText(Html.fromHtml(note.getBody_html()).toString());
        seccion.setText(note.getSeccion());
        tvdate.setText(note.getPubdate() + " " + note.getPubtime());
        tvAutor.setText(note.getAuthor());
        Picasso picasso = Picasso.get();
        picasso.load(note.getImage()).fit()
                .into(image);
        alert.setView(view);
        final AlertDialog alertDialog = alert.create();
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }
}
