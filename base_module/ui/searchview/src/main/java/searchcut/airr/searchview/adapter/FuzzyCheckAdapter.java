package searchcut.airr.searchview.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import searchcut.airr.searchview.icallback.ICallBack;
import searchcut.airr.searchview.R;

/**
 * @author wangfei
 * @date 2019/7/8.
 */
public class FuzzyCheckAdapter extends BaseRecyclerAdapter<String, BaseRecyclerViewHolder> {
    private ICallBack mCallBack;

    public FuzzyCheckAdapter(int layout, ICallBack mCallBack) {
        super(null, layout);
        this.mCallBack = mCallBack;
    }

    @Override
    protected BaseRecyclerViewHolder createMHDViewHolder(Context mContext, View itemView, int viewType) {
        return null;
    }

    @Override
    protected void bindDate(BaseRecyclerViewHolder holder, String string, int position) {
        ((TextView) (holder.getView(R.id.tv_fuzzy_title))).setText(string);

    }

    @Override
    protected void itemClick(Context context, String string) {
        mCallBack.SearchAciton(string);
    }
}
