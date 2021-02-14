package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.bukkit.OfflinePlayer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;

public class Advancements extends PlaceholderExpansion{


    /**
     * This method should always return true unless we
     * have a dependency we need to make sure is on the server
     * for our placeholders to work!
     *
     * @return always true since we do not have any dependencies.
     */
    @Override
    public boolean canRegister(){
        return true;
    }

    /**
     * The name of the person who created this expansion should go here.
     * 
     * @return The name of the author as a String.
     */
    @Override
    public String getAuthor(){
        return "matahombress";
    }

    /**
     * The placeholder identifier should go here.
     * <br>This is what tells PlaceholderAPI to call our onRequest 
     * method to obtain a value if a placeholder starts with our 
     * identifier.
     * <br>This must be unique and can not contain % or _
     *
     * @return The identifier in {@code %<identifier>_<value>%} as String.
     */
    @Override
    public String getName(){
        return "Advancements";
    }
    @Override
    public String getIdentifier(){
        return "Advancements";
    }

    /**
     * This is the version of this expansion.
     * <br>You don't have to use numbers, since it is set as a String.
     *
     * @return The version as a String.
     */
    @Override
    public String getVersion(){
        return "1.1";
    }
    
    
    public String getPlugin() {
        return null;
    }
  
    /**
     * This is the method called when a placeholder with our identifier 
     * is found and needs a value.
     * <br>We specify the value identifier in this method.
     * <br>Since version 2.9.1 can you use OfflinePlayers in your requests.
     *
     * @param  player
     *         A {@link org.bukkit.OfflinePlayer OfflinePlayer}.
     * @param  identifier
     *         A String containing the identifier/value.
     *
     * @return Possibly-null String of the requested identifier.
     */
    @Override
    public String onRequest(final OfflinePlayer player, final String identifier){
        try{
            //%Advancements_<advancement>%
            //%Advancements_player_<player>;<advancement>%
            //%Advancements_playerList_<player>%
            //%Advancements_playerList_<player>,<command>%
            //%Advancements_playerListFormat_<player>%
            //%Advancements_completedAmount_<player>%
            //%Advancements_completedAmount_<player>,<category>%
            //%Advancements_remainingAmount_<player>%
            //%Advancements_remainingAmount_<player>,<category>%
            if(identifier.startsWith("playerList_")){
                String iden_new=identifier.split("playerList_")[1];
                if(iden_new.contains(",")){
                    String[] args=iden_new.split(",");
                    String command=args[1];
                    if(!command.startsWith("/")){
                        command="/"+args[1];
                    }
                    String plName=args[0].trim();
                    OfflinePlayer p=getOfflinePlayer(plName,false);
                    if(!checkPlayerFound(plName,player,p)){
                        return "PLAYER_NOT_FOUND";
                    }
                    Player sender=(Player) player;
                    String abc=listAdvancements(Bukkit.getPlayer(p.getUniqueId()));
                    sender.chat(command+" "+abc);
                    return "";
                }else{
                    String plName=iden_new.trim();
                    OfflinePlayer p=getOfflinePlayer(plName,false);
                    if(!checkPlayerFound(plName,player,p)){
                        return "PLAYER_NOT_FOUND";
                    }
                    String abc=listAdvancements(Bukkit.getPlayer(p.getUniqueId()));
                    return abc;
                }
            }else if(identifier.startsWith("playerListFormat_")){
                String iden_new=identifier.split("playerListFormat_")[1];
                String plName=iden_new.trim();
                OfflinePlayer p=getOfflinePlayer(plName,false);
                if(!checkPlayerFound(plName,player,p)){
                    return "PLAYER_NOT_FOUND";
                }
                String abc=formating(listAdvancements(Bukkit.getPlayer(p.getUniqueId())));
                return abc;
            }else if(identifier.startsWith("player_")){
                String iden_new=identifier.split("player_")[1];
                String[] arr=iden_new.split(";");
                String plName=arr[0];
                OfflinePlayer p=getOfflinePlayer(plName,false);
                if(!checkPlayerFound(plName,player,p)){
                    return "PLAYER_NOT_FOUND";
                }
                String id="";
                int i=0;
                for(String id_2:arr){
                    if(i!=0){
                        if(i==1){
                            id+=""+id_2;
                        }else{
                            id+="_"+id_2;
                        }
                    }
                    i++;
                }
                boolean hadv=hasAdvancement(Bukkit.getPlayer(p.getUniqueId()),id);
                if(hadv==false){
                    return "NO_EXIST_ADVANCEMENT";
                }
                return String.valueOf(hadv);
            }else if(identifier.startsWith("completedAmount_")){
                String iden_new=identifier.split("completedAmount_")[1];
                if(iden_new.contains(",")){
                    String[] args=iden_new.split(",");
                    String category=args[1];
                    String plName=args[0].trim();
                    OfflinePlayer p=getOfflinePlayer(plName,false);
                    if(!checkPlayerFound(plName,player,p)){
                        return "PLAYER_NOT_FOUND";
                    }
                    Integer amount=amountAdvancements(Bukkit.getPlayer(p.getUniqueId()),false,category);
                    if(amount==null){
                        return "CATEGORY_NOT_FOUND";
                    }else{
                        return String.valueOf(amount);
                    }
                }else{
                    String plName=iden_new.trim();
                    OfflinePlayer p=getOfflinePlayer(plName,false);
                    if(!checkPlayerFound(plName,player,p)){
                        return "PLAYER_NOT_FOUND";
                    }
                    Integer amount=amountAdvancements(Bukkit.getPlayer(p.getUniqueId()));
                    return String.valueOf(amount);
                }
            }else if(identifier.startsWith("remainingAmount_")){
                String iden_new=identifier.split("remainingAmount_")[1];
                if(iden_new.contains(",")){
                    String[] args=iden_new.split(",");
                    String category=args[1];
                    String plName=args[0].trim();
                    OfflinePlayer p=getOfflinePlayer(plName,false);
                    if(!checkPlayerFound(plName,player,p)){
                        return "PLAYER_NOT_FOUND";
                    }
                    Integer amount=amountAdvancements(Bukkit.getPlayer(p.getUniqueId()),true,category);
                    if(amount==null){
                        return "CATEGORY_NOT_FOUND";
                    }else{
                        return String.valueOf(amount);
                    }
                }else{
                    String plName=iden_new.trim();
                    OfflinePlayer p=getOfflinePlayer(plName,false);
                    if(!checkPlayerFound(plName,player,p)){
                        return "PLAYER_NOT_FOUND";
                    }
                    Integer amount=amountAdvancements(Bukkit.getPlayer(p.getUniqueId()),true);
                    return String.valueOf(amount);
                }
            }else{
                boolean hadv=hasAdvancement((Player)player,identifier);
                if(hadv==false){
                    return "NO_EXIST_ADVANCEMENT";
                }
                return String.valueOf(hadv);
            }
        }catch(NoPlayerOnline e){
            return "NO_PLAYER_ONLINE";
        }catch(Exception e){
            e.printStackTrace();
            return "NO_WORKING";
        }
        // PlaceholderAPI return null if an invalid placeholder (f.e. %example_placeholder3%) 
        
    }
    //Return list advancements order by categories/type advancements
    public static List<String> getAdvancements(){
        List<String> advancements=new ArrayList<String>();
        List<String> categories=new ArrayList<String>();
        //Obtain diferent type/categories advancements
        for (Iterator<Advancement> iter = Bukkit.getServer().advancementIterator(); iter.hasNext(); ) {
            Advancement adv = iter.next();
            String key=adv.getKey().getKey();
            //Advancement contain too recipes unlocked. Ignored
            if(!key.startsWith("recipes")){
                String parent=key.split("/")[0];
                if(!categories.contains(parent)){
                    categories.add(parent);
                }
            }
        }
        //Get all advancements and order by categories
        for(int i=0;i<categories.size();i++){
            for (Iterator<Advancement> iter = Bukkit.getServer().advancementIterator(); iter.hasNext(); ) {
                Advancement adv = iter.next();
                String key=adv.getKey().getKey();
                if(key.startsWith(categories.get(i))){
                    advancements.add(key);
                }
            }
        }
        return advancements;
    }
    // Return all advancements serialize.
    // Format: <advancement_name>;<if_player_have_advancement>, . . .
    public static String listAdvancements(Player player) throws NoPlayerOnline{
        List<String> advancements=getAdvancements();
        String returnString="";
        for(String ah : advancements){
            returnString+=ah+";"+String.valueOf(hasAdvancement(player,ah));
            returnString+=",";
        }
        return returnString;
    }
    public static Integer amountAdvancements(Player player) throws NoPlayerOnline{
        return amountAdvancements(player,false,null);
    }
    public static Integer amountAdvancements(Player player,boolean isRemaining) throws NoPlayerOnline{
        return amountAdvancements(player,isRemaining,null);
    }
    //Return amount advancements
    public static Integer amountAdvancements(Player player,boolean isRemaining,String category) throws NoPlayerOnline{
        //Why no use getAdvancements()?
        //For amount of loops, and it is not necessary to order them
        int completedAmountAll=0;
        int remainingAmountAll=0;
        List<String> categories=new ArrayList<>();
        HashMap<String,Integer> categoriesAmountCompleted=new HashMap<>();
        HashMap<String,Integer> categoriesAmountRemaining=new HashMap<>();
        //Only 1 loop and sum amount
        for (Iterator<Advancement> iter = Bukkit.getServer().advancementIterator(); iter.hasNext(); ) {
            Advancement adv = iter.next();
            String key=adv.getKey().getKey();
            //Added categories
            boolean isComplete=hasAdvancement(player,key);
            String cat=key.split("/")[0];
            if(key.startsWith("recipes")){
                continue;
            }
            if(!categories.contains(cat)){
                categories.add(cat);
            }
            if(categoriesAmountCompleted.containsKey(cat)){
                if(isComplete){
                    completedAmountAll++;
                    categoriesAmountCompleted.put(cat, categoriesAmountCompleted.get(cat)+1);
                }
            }else{
                if(isComplete){
                    completedAmountAll++;
                    categoriesAmountCompleted.put(cat, 1);
                }else{
                    categoriesAmountCompleted.put(cat, 0);
                }
            }
            if(categoriesAmountRemaining.containsKey(cat)){
                if(!isComplete){
                    remainingAmountAll++;
                    categoriesAmountRemaining.put(cat, categoriesAmountRemaining.get(cat)+1);
                }
            }else{
                if(!isComplete){
                    remainingAmountAll++;
                    categoriesAmountRemaining.put(cat, 1);
                }else{
                    categoriesAmountRemaining.put(cat, 0);
                }
            }
        }
        if(category!=null){
            if(categories.contains(category)){
                if(isRemaining){
                    return categoriesAmountRemaining.get(category);
                }else{
                    return categoriesAmountCompleted.get(category);
                }
            }else{
                return null;
            }
        }else{
            if(isRemaining){
                return remainingAmountAll;
            }else{
                return completedAmountAll;
            }
        }
    }
    //Check if player found
    public static boolean checkPlayerFound(String plName, OfflinePlayer player,OfflinePlayer p){
        if(player==null){
            p=getOfflinePlayer(plName,false);
            if(p==null){
                return false;
            }
        }
        if(p==null){
            return false;
        }
        return true;
    }
    //Parse advancements list and formatted
    public static String formating(String listAdvan){
        return listAdvan.replaceAll(",", "\n").replaceAll(";", ": ").replaceAll("true", ChatColor.GREEN+"true"+ChatColor.RESET).replaceAll("false", ChatColor.RED+"false"+ChatColor.RESET);
    }
    //Check if player have advancement
    public static boolean hasAdvancement(Player player, String achname) throws NoPlayerOnline{
        Advancement ach = null;
        for (Iterator<Advancement> iter = Bukkit.getServer().advancementIterator(); iter.hasNext(); ) {
            Advancement adv = iter.next();
            if (adv.getKey().getKey().equalsIgnoreCase(achname)){
                ach = adv;
                break;
            }
        }
        if(player==null){
            throw new NoPlayerOnline();
        }
        AdvancementProgress prog;
        try{
            prog = player.getAdvancementProgress(ach);
        }catch(IllegalArgumentException e){
            return false;
        }
        return prog.isDone();
    }
    //Get offline player if has entered the server
    public static OfflinePlayer getOfflinePlayer(final String playerStr, final boolean isUUID) {
        OfflinePlayer[] offlinePlayers;
        for (int length = (offlinePlayers = Bukkit.getOfflinePlayers()).length, i = 0; i < length; ++i) {
            final OfflinePlayer p = offlinePlayers[i];
            if (isUUID && p.getUniqueId().toString().equalsIgnoreCase(playerStr)) {
                return p;
            }
            if (p.getName().equalsIgnoreCase(playerStr)) {
                return p;
            }
        }
        return null;
    }
}
class NoPlayerOnline extends Exception{
    public NoPlayerOnline(){
        super("No player Online");
    }
    public NoPlayerOnline(String message){
        super(message);
    }
}