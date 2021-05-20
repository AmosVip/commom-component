//package com.jindashi.ptm.treemap;
//
//import java.util.List;
//
//public abstract class JPTMAbstractMapLayout implements JPTMMapLayout {
//
//    public static final int VERTICAL = 0, HORIZONTAL = 1;
//    public static final int ASCENDING = 0, DESCENDING = 1;
//
//    /**
//     * 计算值的总大小
//     *
//     * @param itemList
//     * @return
//     */
//    public double totalSize(List<JPTMIItemContract> itemList) {
//        return totalSize(itemList, 0, itemList.size() - 1);
//    }
//
//    public double totalSize(List<JPTMIItemContract> itemList, int start, int end) {
//        double sum = 0;
//        for (int i = start; i <= end; i++)
//            sum += itemList.get(i).getCalculateValue();
//        return sum;
//    }
//
//    public void layoutBest(List<JPTMIItemContract> itemList, int start, int end, Rect bounds) {
//        sliceLayout(itemList, start, end, bounds,
//                bounds.w > bounds.h ? HORIZONTAL : VERTICAL);
//    }
//
//    public void sliceLayout(List<JPTMIItemContract> itemList, int start, int end, Rect bounds, int orientation) {
//        double total = totalSize(itemList, start, end);
//        double a = 0;
//        boolean vertical = orientation == VERTICAL;
//
//        for (int i = start; i <= end; i++) {
//            Rect r = new Rect();
//            double b = itemList.get(i).getCalculateValue() / total;
//            if (vertical) {
//                r.x = bounds.x;
//                r.w = bounds.w;
//                /*if (order == ASCENDING)
//                    r.y = bounds.y + bounds.h * a;
//                else
//                    r.y = bounds.y + bounds.h * (1 - a - b);*/
//                r.y = bounds.y + bounds.h * a;
//                r.h = bounds.h * b;
//            } else {
//                /*if (order == ASCENDING)
//                    r.x = bounds.x + bounds.w * a;
//                else
//                    r.x = bounds.x + bounds.w * (1 - a - b);*/
//                r.x = bounds.x + bounds.w * a;
//                r.w = bounds.w * b;
//                r.y = bounds.y;
//                r.h = bounds.h;
//            }
//            itemList.get(i).setBoundRect(r);
//            a += b;
//        }
//    }
//
//    public void sortDescending(List<JPTMIItemContract> itemList) {
//        if (itemList == null && itemList.size() <= 0) {
//            return;
//        }
//        int n = itemList.size();
//        boolean outOfOrder = true;
//        while (outOfOrder) {
//            outOfOrder = false;
//            for (int i = 0; i < n - 1; i++) {
//                boolean wrong = (itemList.get(i).getCalculateValue() < itemList.get(i + 1).getCalculateValue());
//                if (wrong) {
//                    JPTMIItemContract temp = itemList.get(i);
//                    itemList.set(i, itemList.get(i + 1));
//                    itemList.set(i + 1, temp);
//                    outOfOrder = true;
//                }
//            }
//        }
//    }
//}
