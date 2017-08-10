package com.assetowl.android.ui.audits.create.templates;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.assetowl.android.R;
import com.assetowl.android.data.audits.templates.model.TemplateCheck;
import com.assetowl.domain.audits.templates.model.Stats;
import com.assetowl.domain.audits.templates.model.TemplateCheckInfo;
import com.assetowl.domain.audits.templates.model.TemplateInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrickyin on 8/5/17.
 */

public class TemplatesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<? extends TemplateInfo> templatesList;

    private List<TemplateCheckInfo> templateCheckList = new ArrayList<>();
    String question;
    AfterClickListener afterClickListener;
    int topPosition=0;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext().getApplicationContext())
                .inflate(R.layout.recyclerview_templates_list_row, parent, false);
        question = parent.getContext().getApplicationContext().getString(R.string.questions);
        return new TemplatesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof TemplatesListViewHolder) {
            TemplatesListViewHolder viewHolder = (TemplatesListViewHolder) holder;
            viewHolder.textViewId.setText(this.templatesList.get(position).getIdString());
            viewHolder.textViewAuditTemplates.setText(this.templatesList.get(position).getName());
            Stats stats = this.templatesList.get(position).getStats();
            viewHolder.textViewQuestions.setText(stats != null ? stats.getTotalQuestionsString() + " " + question : "");

            viewHolder.checkedTemplate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    templateCheckList.get(position).setTemplateCheck(isChecked);
                    afterClickListener.afterClick();

                    if(isChecked)
                    {
                        notifyItemMoved(viewHolder.getAdapterPosition(),topPosition);
                        topPosition++;
                    }
                    else
                    {
                        topPosition--;
                        notifyItemMoved(viewHolder.getAdapterPosition(),topPosition);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(this.templatesList == null) {
            return 0;
        } else {
            return this.templatesList.size();
        }
    }




    public void setTemplatesList(List<? extends TemplateInfo> templatesList) {
        this.templatesList = templatesList;
        this.templateCheckList.clear();
        for (TemplateInfo templateInfo: templatesList) {
            this.templateCheckList.add(new TemplateCheck(templateInfo.getId(), false));
        }
    }

    public List<TemplateCheckInfo> getTemplateCheckList() {
        return templateCheckList;
    }

    public int getCheckedItemsCount() {
        int count = 0;
        for(TemplateCheckInfo templateCheck: templateCheckList) {
            if(templateCheck.isTemplateCheck()) count++;
        }
        return count;
    }

    public void setAfterClickListener(AfterClickListener afterClickListener) {
        this.afterClickListener = afterClickListener;
    }

    public static interface AfterClickListener {
        void afterClick();
    }

}
