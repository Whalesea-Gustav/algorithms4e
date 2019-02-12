package chapter4.section1;

public interface GraphAPI {
    int V(); // 返回定点数
    int E(); // 返回边数
    void addEdge(int v, int w); // 添加一条边 v-w
    Iterable<Integer> adj(int v); // 返回包含所有定点的可迭代容器
    @Override
    String toString(); //返回对象的字符串表示
}
