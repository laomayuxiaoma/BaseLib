package searchcut.airr.searchview.model;

import searchcut.airr.searchview.icallback.ICallBack;

/**
 * @author wangfei
 * @date 2019/7/31.
 */
public abstract class SearchModelDto {

    private ICallBack callBack;

    public ICallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(ICallBack callBack) {
        this.callBack = callBack;
    }
}
