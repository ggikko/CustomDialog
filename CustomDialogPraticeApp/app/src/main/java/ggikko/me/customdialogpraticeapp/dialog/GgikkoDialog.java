package ggikko.me.customdialogpraticeapp.dialog;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
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
    private String centerContent;
    private String description;
    private String positiveText;
    private String negativeText;
    private int subTitleTextSize;

    @BindView(R.id.titleTextView) TextView titleTextView;
    @BindView(R.id.subTitleTextView) TextView subTitleTextView;
    @BindView(R.id.centerContentTextView) TextView centerContentTextView;
    @BindView(R.id.descriptionTextView) TextView descriptionTextView;

    @BindView(R.id.negativeTextView) TextView negativeTextView;
    @BindView(R.id.positiveTextView) TextView positiveTextView;

    public GgikkoDialog(Builder builder) {
        this.positiveButtonListener = builder.positiveButtonListener;
        this.negativeButtonListener = builder.negativeButtonListener;
        this.title = builder.title;
        this.subTitle = builder.subTitle;
        this.centerContent = builder.centerContent;
        this.description = builder.description;
        this.positiveText = builder.positiveText;
        this.negativeText = builder.negativeText;
        this.subTitleTextSize = builder.subTitleTextSize;
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
        if (isStringEmpty(description)) descriptionTextView.setVisibility(View.GONE);
        if (isStringEmpty(centerContent)) centerContentTextView.setVisibility(View.GONE);
        if (isStringEmpty(positiveText)) positiveTextView.setVisibility(View.GONE);
        if (isStringEmpty(negativeText)) negativeTextView.setVisibility(View.GONE);

        titleTextView.setText(title);
        subTitleTextView.setText(subTitle);
        descriptionTextView.setText(description);
        subTitleTextView.setTextSize(subTitleTextSize);
        centerContentTextView.setText(centerContent);
        positiveTextView.setText(positiveText);
        negativeTextView.setText(negativeText);
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
        private String centerContent;
        private String description;
        private String positiveText;
        private String negativeText;
        private int subTitleTextSize;

        public Builder(Context context, FragmentManager fragmentManager, String tag) {
            this.context = context;
            this.fragmentManager = fragmentManager;
            this.tag = tag;
        }

        public GgikkoDialog build() {
            return new GgikkoDialog(this);
        }

        public Builder positiveListener(PositiveButtonListener positiveButtonListener) {
            this.positiveButtonListener = positiveButtonListener;
            return this;
        }

        public Builder negativeListener(NegativeButtonListener negativeButtonListener) {
            this.negativeButtonListener = negativeButtonListener;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder subTitle(String subTitle, int subTitleTextSize) {
            this.subTitle = subTitle;
            this.subTitleTextSize= subTitleTextSize;
            return this;
        }

        public Builder centerContent(String centerContent) {
            this.centerContent = centerContent;
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

        public GgikkoDialog show() {
            GgikkoDialog dialog = build();
            dialog.show(fragmentManager, tag);
            return dialog;
        }
    }
}
