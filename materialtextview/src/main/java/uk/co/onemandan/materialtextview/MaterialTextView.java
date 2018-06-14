package uk.co.onemandan.materialtextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MaterialTextView extends LinearLayout implements View.OnClickListener {
    private TextView    _labelView;
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
    private int _backgroundColour;

    private boolean _keepLabelSpacing;  //Whether or not spacing should be kept if there is no label text
    private boolean _keepHelperSpacing; //"                                   " if there is no helper text

    //Default colours to use for each View
    private int _DEFAULT_LABEL_TEXT_COLOUR;     //android:textColorSecondary
    private int _DEFAULT_CONTENT_TEXT_COLOUR;   //android:textColorPrimary
    private int _DEFAULT_HELPER_TEXT_COLOUR;    //colorAccent
    private int _DEFAULT_BACKGROUND_COLOUR;     //6% Black

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
        _DEFAULT_BACKGROUND_COLOUR    = context.getResources().getColor(R.color.TintBlack06);

        ta.recycle();
    }

    private void handleAttributes(Context context, AttributeSet attrs){
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MaterialTextView,
                0, 0);

        //Misc
        _keepLabelSpacing   = ta.getBoolean(R.styleable.MaterialTextView_mtv_keepLabelSpacing,
                false);
        _keepHelperSpacing  = ta.getBoolean(R.styleable.MaterialTextView_mtv_keepHelperSpacing,
                false);

        //Text
        setLabelText(ta.getString(R.styleable.MaterialTextView_mtv_labelText) == null ? "" :
                ta.getString(R.styleable.MaterialTextView_mtv_labelText));
        setContentText(ta.getString(R.styleable.MaterialTextView_mtv_contentText) == null ? "" :
                ta.getString(R.styleable.MaterialTextView_mtv_contentText));
        setHelperText(ta.getString(R.styleable.MaterialTextView_mtv_helperText) == null ? "" :
                ta.getString(R.styleable.MaterialTextView_mtv_helperText));

        //Colours
        setLabelTextColour(ta.getColor(R.styleable.MaterialTextView_mtv_labelTextColor,
                _DEFAULT_LABEL_TEXT_COLOUR));
        setContentTextColour(ta.getColor(R.styleable.MaterialTextView_mtv_contentTextColor,
                _DEFAULT_CONTENT_TEXT_COLOUR));
        setHelperTextColour(ta.getColor(R.styleable.MaterialTextView_mtv_helperTextColor,
                _DEFAULT_HELPER_TEXT_COLOUR));
        setBackgroundColour(ta.getColor(R.styleable.MaterialTextView_mtv_backgroundColor,
                _DEFAULT_BACKGROUND_COLOUR));

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

    // SETTERS
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("unused")
    public void setBackgroundColour(int colour){
        _backgroundColour = colour;
        _rootView.getBackground().setColorFilter(new PorterDuffColorFilter(_backgroundColour,
                PorterDuff.Mode.SRC_IN));
    }

    @SuppressWarnings("unused")
    public void setLabelTextColour(int colour){
        _labelTextColour = colour;
        _labelView.setTextColor(_labelTextColour);
    }

    @SuppressWarnings("unused")
    public void setContentTextColour(int colour){
        _contentTextColour = colour;
        _contentView.setTextColor(_contentTextColour);
    }

    @SuppressWarnings("unused")
    public void setHelperTextColour(int colour){
        _helperTextColour = colour;
        _helperView.setTextColor(_helperTextColour);
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

    // GETTERS
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @SuppressWarnings("unused")
    public int getBackgroundColour(){
        return _backgroundColour;
    }

    @SuppressWarnings("unused")
    public int getLabelTextColour(){ return _labelTextColour; }

    @SuppressWarnings("unused")
    public int getContentTextColour(){ return _contentTextColour; }

    @SuppressWarnings("unused")
    public int getHelperTextColour(){ return _helperTextColour; }

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
