//
//import com.osu.backend.model.crane.Crane;
//import com.osu.backend.model.ship.Ship;
//
//import java.lang.*;
//import java.util.List;
//
//public class CraneThread extends Thread{
//
//    private List<Crane> activityCranes;
//    private Ship activityShip;
//     CraneThread(String name, Ship ship, List<Crane> crane) {
//         super(name);
//         for (int i = 0; i < crane.size(); i++) {
//             if (crane.get(i).getStatus() == "0"){
//                 if (crane.get(i).getCrane_type().getId() == ship.getShip_type().getId()){
//
//                    activityCranes.add(crane.get(i));
//                 }
//             }
//         }
//     }
//     public void run(){
//         activityCranes.get(0).setStatus("1");
//         Integer result =  this.activityShip.getValume();
//         for (int i = 0; i < this.activityShip.getValume() / activityCranes.get(0).getCrane_type().getSpeed() + 1; i++) {
//             result -=  activityCranes.get(0).getCrane_type().getSpeed();
//             try{
//                 Thread.sleep(100);
//             }
//             catch (InterruptedException e){
//                 System.out.println("Thread has been interrupted");
//             }
//         }
//         activityCranes.get(0).setStatus("0");
//     }
//}
