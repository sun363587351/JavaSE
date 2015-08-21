package expert.designPattern.proxy.staticProxy;

/**
 * Created by IFT8
 * on 2015/8/17.
 */
public class DaoProxy implements Dao {
    private Dao dao;
    private Transaction transaction;

    public DaoProxy(Dao dao, Transaction transaction) {
        this.dao = dao;
        this.transaction = transaction;
    }

    @Override
    public void add() {
        this.transaction.beginTransation();
        this.dao.add();
        this.transaction.commit();
    }

    @Override
    public void del() {
        this.transaction.beginTransation();
        this.dao.del();
        this.transaction.commit();
    }

    @Override
    public void update() {
        this.transaction.beginTransation();
        this.dao.update();
        this.transaction.commit();
    }

    @Override
    public void read() {
        this.transaction.beginTransation();
        this.dao.read();
        this.transaction.commit();
    }
}
