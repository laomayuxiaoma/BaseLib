//package com.mhd.baseui.shade.util;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//
//import com.mhd.baseui.R;
//
///**
// * @author zhangming
// * @Date 2019/8/12 17:15
// * @Description: ${todo}(描述该类的功能)
// */
//public class CustomLayoutInflater extends LayoutInflater {
//
////    private PFragment mPFragment;
//
//    protected CustomLayoutInflater(Context context) {
//        super(context);
//    }
//
//    protected CustomLayoutInflater(LayoutInflater original, Context newContext) {
//        super(original, newContext);
//        setFactory2(new CustomFactory(this));
//    }
//
////    protected CustomLayoutInflater(LayoutInflater original, Context newContext, PFragment pFragment) {
////        super(original, newContext);
////        mPFragment = pFragment;
////        setFactory2(new CustomFactory(this));
////    }
//
//    @Override
//    public LayoutInflater cloneInContext(Context newContext) {
//        return new CustomLayoutInflater(this, newContext);
//    }
//
//    class CustomFactory implements Factory2 {
//
//        private final String[] sClassPrefix = {
//                "android.widget.",
//                "android.view."
//        };
//
//        int[] attrIds = {
//                R.attr.x_in,
//                R.attr.x_out,
//                R.attr.y_in,
//                R.attr.y_out,
//        };
//
//        private LayoutInflater mLayoutInflater;
//
//        public PFactory(LayoutInflater layoutInflater) {
//            mLayoutInflater = layoutInflater;
//        }
//
//        @Override
//        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
//            Log.e("TAG", "OnCreateView:" + parent + " Name:" + name + " AttributeSet:" + attrs);
//            View view = createMyView(name, context, attrs);
//            if (view != null) {
//                TypedArray typedArray = context.obtainStyledAttributes(attrs, attrIds);
//                if (typedArray != null && typedArray.length() > 0) {
//                    ViewTag viewTag = new ViewTag();
//                    viewTag.xIn = typedArray.getFloat(0,0);
//                    viewTag.xOut = typedArray.getFloat(1,0);
//                    viewTag.yIn = typedArray.getFloat(2,0);
//                    viewTag.yOut = typedArray.getFloat(3,0);
//
//                    view.setTag(R.id.view_tag,viewTag);
//
//                    //todo获取到自定义属性的View
//                    typedArray.recycle();
//                }
//            }
//            return view;
//        }
//
//        @Override
//        public View onCreateView(String name, Context context, AttributeSet attrs) {
//            Log.e("TAG", " Name:" + name + " AttributeSet:" + attrs);
//            return null;
//        }
//
//        private View createMyView(String name, Context context, AttributeSet attrs) {
//            View view;
//            if (name.contains(".")) {
//                view = reflectView(name, null, context, attrs);
//            } else {
//                for (String prefix : sClassPrefix) {
//                    view = reflectView(name, prefix, context, attrs);
//                    if (view != null) {
//                        return view;
//                    }
//                }
//            }
//            return null;
//        }
//
//        private View reflectView(String name, String prefix, Context context, AttributeSet attrs) {
//            try {
//                return mLayoutInflater.createView(name, prefix, attrs);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//    }
//}
