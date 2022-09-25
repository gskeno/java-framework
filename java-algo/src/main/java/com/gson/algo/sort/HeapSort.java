package com.gsonkeno.dsaa.sort;

/**堆排序
 *
 * 参考: https://www.cnblogs.com/MOBIN/p/5374217.html
 *
 * Created by gaosong on 2017-07-09.
 */
public class HeapSort {

    private static int[] sort=new int[]{1,0,10,20,3,5,6,4,9,8,12,
            17,34,11};

    public static void main(String[] args){
        //建初始堆
        buildMaxHeapify(sort);
        //调整堆
        heapSort(sort);

        print(sort);
    }


    private static void buildMaxHeapify(int[] data){
        //没有子节点的才需要创建最大堆，从最后一个的父节点开始
        int startIndex=getParentIndex(data.length-1);
        //从尾端开始创建最大堆，每次都是正确的堆
        for(int i=startIndex;i>=0;i--){
            maxHeapify(data,data.length,i);
        }
    }

    /**
     *创建最大堆
     *
     *@param data
     *@param heapSize 需要创建最大堆的大小，一般在sort的时候用到，因为最大值放在末尾，末尾就不再归入最大堆了
     *@param index 当前需要创建最大堆的位置
     */
    private static void maxHeapify(int[] data,int heapSize,int index){
        //当前点与左右子节点比较
        int left=getChildLeftIndex(index);
        int right=getChildRightIndex(index);

        int largest=index;

        if(left<heapSize&&data[index]<data[left]){
            largest=left;
        }
        if(right<heapSize&&data[largest]<data[right]){
            largest=right;
        }
        //得到最大值后可能需要交换，如果交换了，其子节点可能就不是最大堆了，需要重新调整
        if(largest!=index){
            int temp=data[index];
            data[index]=data[largest];
            data[largest]=temp;
            maxHeapify(data,heapSize,largest);
        }
    }

    /**
     *排序，最大值放在末尾，data虽然是最大堆，在排序后就成了递增的
     *
     *@param data
     */
    private static void heapSort(int[] data){
        //末尾与头交换，交换后调整最大堆
        for(int i=data.length-1;i>0;i--){
            int temp=data[0];
            data[0]=data[i];
            data[i]=temp;
            //点睛之笔 , i表示需要创建最大堆的元素个数，每次排序后，最大的元素就出来了，不必再参与最大堆排序
            // index ==0 表示， 对首节点进行最大堆调整即可，因为在这之前已经是最大堆了，现在只不过是堆顶元素被换了而已
            maxHeapify(data,i,0);
        }
    }

    /**
     *父节点位置
     *          0
     *     1          2
     *   3   4     5       6
     * 7  8 9 10 11 12  13 14
     *@param current
     *@return
     */
    private static int getParentIndex(int current){
        return(current-1)>>1;
    }

    /**
     *左子节点position注意括号，加法优先级更高
     *
     *@param current
     *@return
     */
    private static int getChildLeftIndex(int current){
        return(current<<1)+1;
    }

    /**
     *右子节点position
     *右子节点序号是父节点序号的2倍+2
     *@param current
     *@return
     */
    private static int getChildRightIndex(int current){
        return(current<<1)+2;
    }

    private static void print(int[] data){
        int pre=-2;
        for(int i=0;i<data.length;i++){
            if(pre<(int)getLog(i+1)){
                pre=(int)getLog(i+1);
                System.out.println();
            }
            System.out.print(data[i]+"|");
        }
    }

    /**
     *以2为底的对数
     *
     *@param param
     *@return
     */
    private static double getLog(double param){
        return Math.log(param)/Math.log(2);
    }
}
