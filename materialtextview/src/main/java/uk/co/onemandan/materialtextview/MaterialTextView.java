package uk.co.onemandan.materialtextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MaterialTextView extends LinearLayout implements View.OnClickListener {
    private TextView _labelView;
    private TextView    _contentView;
    private TextView    _helperView;
    private FrameLayout _rootView;      //Root view is a FrameLayout, as setForeground is available in
                                        //API 1, whereas setForeground in View is only available in
                                        //API 23 and above

    private CharSequence _labelText;
    private CharSequence _contentText;
    private CharSequence _helperText;

    private int _labelTextColour;
    private int _contentTextColour;
    private int _helperTextColour;

    private boolean _keepLabelSpacing;  //Whether or not spacing should be kept if there is no label text
    private boolean _keepHelperSpacing; //"                                   " if there is no helper text

    //Default colours to use for each View
    private int _DEFAULT_LABEL_TEXT_COLOUR;     //android:textColorSecondary
    private int _DEFAULT_CONTENT_TEXT_COLOUR;   //android:textColorPrimary
    private int _DEFAULT_HELPER_TEXT_COLOUR;    //colorAccent

    private Drawable _DEFAULT_CLICKABLE_FOREGROUND;

    private OnClickListener _onClickListener;

    public MaterialTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        View root       = inflate(context, R.layout.view_material_text_view, this);
        _rootView       = root.findViewById(R.id.ll_mtv_root);
        _labelView      = root.findViewById(R.id.tv_mtv_label);
        _contentView    = root.findViewById(R.id.tv_mtv_content);
        _helperView     = root.findViewById(R.id.tv_mtv_helper);

        _rootView.setOnClickListener(this);

        handleDefaultAttributes(context);
        handleAttributes(context, attrs);
        initViews();
    }

    private void initViews(){
        _labelView.setTextColor(_labelTextColour);
        _contentView.setTextColor(_contentTextColour);
        _helperView.setTextColor(_helperTextColour);

        _labelView.setText(_labelText);
        _contentView.setText(_contentText);
        _helperView.setText(_helperText);

        handleHelperVisibility();
        handleLabelVisibility();
    }

    private void handleDefaultAttributes(Context context){
        int[] themeAttrs = new int[] {
                android.R.attr.textColorSecondary,
                android.R.attr.textColorPrimary,
                R.attr.colorAccent,
                android.R.attr.selectableItemBackground
        };

        TypedArray ta = context.getTheme().obtainStyledAttributes(themeAttrs);

        _DEFAULT_LABEL_TEXT_COLOUR    = ta.getColor(0, 0);
        _DEFAULT_CONTENT_TEXT_COLOUR  = ta.getColor(1, 0);
        _DEFAULT_HELPER_TEXT_COLOUR   = ta.getColor(2, 0);
        _DEFAULT_CLICKABLE_FOREGROUND = ta.getDrawable(3);

        ta.recycle();
    }

    private void handleAttributes(Context context, AttributeSet attrs){
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MaterialTextView,
                0, 0);

        _labelText      = ta.getString(R.styleable.MaterialTextView_mtv_labelText) == null ? "" :
                ta.getString(R.styleable.MaterialTextView_mtv_labelText);
        _contentText    = ta.getString(R.styleable.MaterialTextView_mtv_contentText) == null ? "" :
                ta.getString(R.styleable.MaterialTextView_mtv_contentText);
        _helperText     = ta.getString(R.styleable.MaterialTextView_mtv_helperText) == null ? "" :
                ta.getString(R.styleable.MaterialTextView_mtv_helperText);

        _labelTextColour    = ta.getColor(R.styleable.MaterialTextView_mtv_labelTextColor,
                _DEFAULT_LABEL_TEXT_COLOUR);
        _contentTextColour  = ta.getColor(R.styleable.MaterialTextView_mtv_contentTextColor,
                _DEFAULT_CONTENT_TEXT_COLOUR);
        _helperTextColour   = ta.getColor(R.styleable.MaterialTextView_mtv_helperTextColor,
                _DEFAULT_HELPER_TEXT_COLOUR);

        _keepLabelSpacing   = ta.getBoolean(R.styleable.MaterialTextView_mtv_keepLabelSpacing,
                true);
        _keepHelperSpacing  = ta.getBoolean(R.styleable.MaterialTextView_mtv_keepHelperSpacing,
                false);

        ta.recycle();
    }

    private void handleHelperVisibility(){
        if(_helperText.length() == 0 && !_keepHelperSpacing && _helperView.getVisibility() != View.GONE){
            _helperView.setVisibility(View.GONE);
        } else if (_helperView.getVisibility() != View.VISIBLE){
            _helperView.setVisibility(View.VISIBLE);
        }
    }

    private void handleLabelVisibility(){
        if(_labelText.length() == 0 && !_keepLabelSpacing && _labelView.getVisibility() != View.GONE){
            _labelView.setVisibility(View.GONE);
        } else if(_labelView.getVisibility() != View.VISIBLE){
            _labelView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if(_onClickListener != null){
            _onClickListener.onClick(v);
        }
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        if(l != null){
            _rootView.setForeground(_DEFAULT_CLICKABLE_FOREGROUND);
            _rootView.setClickable(true);
            _rootView.setFocusable(true);
        } else {
            _rootView.setForeground(null);
            _rootView.setClickable(false);
            _rootView.setFocusable(false);
        }

        _onClickListener = l;
    }

    @SuppressWarnings("unused")
    public void setLabelText(CharSequence text){
        _labelText = text;
        _labelView.setText(_labelText);

        handleLabelVisibility();
    }

    @SuppressWarnings("unused")
    public void setContentText(CharSequence text){
        _contentText = text;
        _contentView.setText(_contentText);
    }

    @SuppressWarnings("unused")
    public void setHelperText(CharSequence text){
        _helperText = text;
        _helperView.setText(_helperText);

        handleHelperVisibility();
    }

    @SuppressWarnings("unused")
    public CharSequence getLabelText(){
        return _labelText;
    }

    @SuppressWarnings("unused")
    public CharSequence getContentText(){
        return _contentText;
    }

    @SuppressWarnings("unused")
    public CharSequence getHelperText(){
        return _helperText;
    }
}
