package com.assetowl.android.ui.audits.create.templates;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.assetowl.android.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by patrickyin on 8/5/17.
 */

public class TemplatesListViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.textview_id)
    TextView textViewId;
    @Bind(R.id.textview_audit_templates)
    TextView textViewAuditTemplates;
    @Bind(R.id.textview_questions)
    TextView textViewQuestions;
    @Bind(R.id.chb_template)
    CheckBox checkedTemplate;

    public TemplatesListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedTemplate.setChecked(!checkedTemplate.isChecked());
            }
        });
    }
}
