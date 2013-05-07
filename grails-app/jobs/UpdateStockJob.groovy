import com.pember.downturn.Stock
import com.pember.downturn.StockService
import org.quartz.Job

class UpdateStockJob {
    StockService stockService

    static triggers = {
        cron name: 'myTrigger', cronExpression: "0/30 * * * * ?"
    }

    void execute() {
        //stockService.updateAllStocks()
    }
}