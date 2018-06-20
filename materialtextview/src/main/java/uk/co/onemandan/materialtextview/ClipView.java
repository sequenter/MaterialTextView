package uk.co.onemandan.materialtextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

class ClipView extends FrameLayout {

    private Context _context;
    private Float   _cornerRadius;
    private Rect    _rect           = new Rect();
    private RectF   _rectF          = new RectF();
    private Path    _clipPath       = new Path();

    public ClipView(Context context) {
        super(context);

        _context = context;
        init();
    }

    public ClipView(Context context, AttributeSet attrs) {
        super(context, attrs);

        _context = context;
        init();
    }

    public ClipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        _context = context;
        init();
    }

    protected void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        _cornerRadius = _context.getResources().getDimension(R.dimen.corner_radius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.getClipBounds(_rect);

        _rectF.set(_rect);
        _clipPath.reset();
        _clipPath.addRoundRect(_rectF, _cornerRadius, _cornerRadius, Path.Direction.CW);

        canvas.clipPath(_clipPath);

        super.onDraw(canvas);
    }
}
