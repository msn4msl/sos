
package irrigationmanagementsystem;

/**
 *
 * @author Sandeepa Madhusanka
 */
public class NewItemDetails {
    private String category;
    private String sBook;
    private int page;
    private int itemID;
    private String item;
    private int serialNo;
    private String itemDescription;

    /**
     * @return the sBook
     */
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getsBook() {
        return sBook;
    }

    /**
     * @param sBook the sBook to set
     */
    public void setsBook(String sBook) {
        this.sBook = sBook;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the itemID
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * @param itemID the itemID to set
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    /**
     * @return the item
     */
    public String getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * @return the serialNo
     */
    public int getSerialNo() {
        return serialNo;
    }

    /**
     * @param serialNo the serialNo to set
     */
    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    /**
     * @return the itemDescription
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * @param itemDescription the itemDescription to set
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
}
