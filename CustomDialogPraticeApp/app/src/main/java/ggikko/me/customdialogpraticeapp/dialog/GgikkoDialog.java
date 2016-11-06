package ggikko.me.customdialogpraticeapp.dialog;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ggikko.me.customdialogpraticeapp.R;

public class GgikkoDialog extends BaseDialog {

    private PositiveButtonListener positiveButtonListener;
    private NegativeButtonListener negativeButtonListener;
    private String title;
    private String subTitle;
    private String content;
    private String boxContent;
    private String description;
    private String positiveText;
    private String negativeText;
    private int subTitleTextSize;
    private boolean isHtml;
    private boolean isDivider;

    @BindView(R.id.titleTextView) TextView titleTextView;
    @BindView(R.id.subTitleTextView) TextView subTitleTextView;
    @BindView(R.id.contentTextView) TextView contentTextView;
    @BindView(R.id.boxContentTextView) TextView boxContentTextView;
    @BindView(R.id.descriptionTextView) TextView descriptionTextView;

    @BindView(R.id.negativeTextView) TextView negativeTextView;
    @BindView(R.id.positiveTextView) TextView positiveTextView;

    @BindView(R.id.dividerView) View dividerView;

    public GgikkoDialog(){

    }

    public GgikkoDialog(Builder builder) {
        this.positiveButtonListener = builder.positiveButtonListener;
        this.negativeButtonListener = builder.negativeButtonListener;
        this.title = builder.title;
        this.subTitle = builder.subTitle;
        this.content = builder.content;
        this.boxContent = builder.boxContent;
        this.description = builder.description;
        this.positiveText = builder.positiveText;
        this.negativeText = builder.negativeText;
        this.subTitleTextSize = builder.subTitleTextSize;
        this.isHtml = builder.isHtml;
        this.isDivider = builder.isDivider;
    }

    @OnClick(R.id.positiveTextView)
    void positiveTextViewClicked() {
        positiveButtonListener.onClick(this);
    }

    @OnClick(R.id.negativeTextView)
    void negativeTextViewClicked() {
        negativeButtonListener.onClick(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (savedInstanceState != null) dismiss();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ggikko_dialog, container, false);
        ButterKnife.bind(this, view);
        setUpViews();
        return view;
    }

    private void setUpViews() {
        if (isNull(positiveButtonListener)) positiveTextView.setVisibility(View.GONE);
        if (isNull(negativeButtonListener)) negativeTextView.setVisibility(View.GONE);

        if (isStringEmpty(title)) titleTextView.setVisibility(View.GONE);
        if (isStringEmpty(subTitle)) subTitleTextView.setVisibility(View.GONE);
        if (isStringEmpty(content)) contentTextView.setVisibility(View.GONE);
        if (isStringEmpty(description)) descriptionTextView.setVisibility(View.GONE);
        if (isStringEmpty(boxContent)) boxContentTextView.setVisibility(View.GONE);
        if (isStringEmpty(positiveText)) positiveTextView.setVisibility(View.GONE);
        if (isStringEmpty(negativeText)) negativeTextView.setVisibility(View.GONE);
        if (isDivider) dividerView.setVisibility(View.VISIBLE);

        titleTextView.setText(title);
        contentTextView.setText(content);
        descriptionTextView.setText(description);
        boxContentTextView.setText(boxContent);
        positiveTextView.setText(positiveText);
        negativeTextView.setText(negativeText);

        if(isHtml){
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                subTitleTextView.setText(Html.fromHtml(subTitle, Html.FROM_HTML_MODE_LEGACY));
            } else {
                subTitleTextView.setText(Html.fromHtml(subTitle));
            }
        }else{
            subTitleTextView.setText(subTitle);
        }
    }

    private boolean isStringEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    private boolean isNull(Object object) {
        return object == null ? true : false;
    }

    public static class Builder {

        private Context context;
        private FragmentManager fragmentManager;
        private String tag;
        private PositiveButtonListener positiveButtonListener;
        private NegativeButtonListener negativeButtonListener;
        private String title;
        private String subTitle;
        private String content;
        private String boxContent;
        private String description;
        private String positiveText;
        private String negativeText;
        private int subTitleTextSize;
        private boolean isHtml = false;
        private boolean isDivider = false;

        public Builder(Context context, FragmentManager fragmentManager, String tag) {
            this.context = context;
            this.fragmentManager = fragmentManager;
            this.tag = tag;
        }

        public GgikkoDialog build() {
            return new GgikkoDialog(this);
        }

        public Builder onPositive(PositiveButtonListener positiveButtonListener) {
            this.positiveButtonListener = positiveButtonListener;
            return this;
        }

        public Builder onNegative(NegativeButtonListener negativeButtonListener) {
            this.negativeButtonListener = negativeButtonListener;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder subTitle(String subTitle, @Nullable boolean isHtml) {
            this.subTitle = subTitle;
            this.isHtml = isHtml;
            return this;
        }

        public Builder subTitle(String subTitle) {
            this.subTitle = subTitle;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder boxContent(String boxContent) {
            this.boxContent = boxContent;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder positiveText(String positiveText) {
            this.positiveText = positiveText;
            return this;
        }

        public Builder negativeText(String negativeText) {
            this.negativeText = negativeText;
            return this;
        }

        public Builder useDivider(){
            isDivider = true;
            return this;
        }

        public GgikkoDialog show() {
            GgikkoDialog dialog = build();
            dialog.show(fragmentManager, tag);
            return dialog;
        }
    }
}
