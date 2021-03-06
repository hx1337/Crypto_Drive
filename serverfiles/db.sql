/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.5.8-log : Database - crypsml
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`crypsml` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `crypsml`;

/*Table structure for table `backup` */

DROP TABLE IF EXISTS `backup`;

CREATE TABLE `backup` (
  `fid` int(20) NOT NULL AUTO_INCREMENT,
  `uid` int(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `file` longblob,
  `type` varchar(30) DEFAULT NULL,
  `key` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `backup` */

/*Table structure for table `image_file` */

DROP TABLE IF EXISTS `image_file`;

CREATE TABLE `image_file` (
  `fid` int(20) NOT NULL AUTO_INCREMENT,
  `uid` int(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `image` longblob,
  `key` varchar(30) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `image_file` */

insert  into `image_file`(`fid`,`uid`,`name`,`image`,`key`,`status`) values (17,3,'life','JAAQSKZJRGABAQAAAQABAADGIOSUNDXBSTZJTEUAAQEAAAIYAAAAAAIQAABTBNRYUKDCIFHZWIAAAAAAAAAAAAAAAABHYNWAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQAATYAAQAAAADTLQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAALKZXNJAAAAAAAAHRYWFLAAAABZAAAABRNWFLAAAABEAAAABRIWFLAAAABJAAAABRYVFJDAAABOAAAACHNVFJDAAABOAAAACHIVFJDAAABOAAAACHDHBAAABYAAAABRJCHJAAABAAAADXTBHVJAAAAAAAAAAEAAAAMZWVUWAAAFGAAAACAHMAUGBHAEIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFHZWIAAAAAAAABVOGAAOPUAAAOQWFLAIAAAAAAAAGKZAACHQAAGNPYWVOGAAAAAAAAJKAAAAEAACZBHCMEAAAAAAAQAAAACZMYAAPKNAAANWQAAEAAAAPBAAAAAAAAAABYWVOGAAAAAAAATYAAQAAAADTLWSDWMAAAAAAAAAAQAAAAXLBLVTAAAAIAAAABWARWBVAGAZWBSAGUAIABJAGAYWAUACAAMGAWADEANVBAEMAAWICAWICAWMDAWQDAWQFCAUFBAQFCGCHBGGMCGWMCWOLCWOEHANDHEOCWSQFHAREXQVFRUMDXCYFHQYEHQVFPBAEMBAWQEBQQFCQUFCRQNCWUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFPAABEIAHWBDGMBIGACEQEDEQHXAADAAEAAWADAQEBAAAAAAAAAAAABQYHAQMEAGGJQAPBAAAQQBAGMECQMCBQQDAAAAAAECAWQFBHEHEIETFJHRCBQINVFSCKXFSNBMMEKQOGRWQKZCAEXCVDXAAUAQEAAAAAAAAAAAAAAAAAAAAAQAFBEBAAAAAAAAAAAAAAAAAAAAAPAAAWDAQACEQMRADALUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABOHCVLTFLKRZJFBKDNKYCLBPLBJVFWDQVJLYEQQVJLYERMGCGQVJLYEQQVJLYERMGCGQVJLYEQQVJLYERMGCGQVJLYEQQVJLYERMGCGQVJLYEQQVJLYERMGCGQVJLYEQQVJLYERMGCGQVJLYEQQVJLYERMGCGQVJLYEQQVJLYERMGCGQVJLYEQQVJLYERMGCGQVJLYEQQVJLYERMGCGQVJLYEQQVJLYERMGCGQVJLYEQQVJLYERMGCGQVJLYEQQVJLYERMGCKZHXYYKLMVJRTLVXYIMWOPJXEIAAAAAACSYFVXNKKWSXUTQHOEAAAAAAAAAAABQGXGDINGEGROIFRTUVOSCKRNCRFGMEYKUERTNDOZBPMAZEAAANBXJXFIJONQVTMHRVCBIVDIMHARGOCXYOJNOVRZHJFWUFHZWBXANJNKAUOFQUEYTNTQULZHNJGRTVVGPSXJLVUAKDVEFXOATZFDHGQOFJBMWSFFLYOGWSQZOBFZCQCQKIQQBDFKXPBSDVQOAXWOTQDLDBNJXXRNJJJGKKXVEVSUAVAKAUAAAAAAAAAAAABUTWOPJXEITERFEUFKLIEAAAAAABBDJEZPQRGLSLBPLBJVFWCBAAAAAAAAAAAYMVKYNAEUUFLSRUXYMHMEUNWCPBDDWAOFQDWXGFHPMFQORWPPZDUTTTTITZXNNJIMPLEISJNDZPPVYNERWVDCJDPUUWAZXUZJZWUWVCVXWYRQJJZWGIVRZGZCLURWMFJLJPUSRJPGJGQXQJLDOYFZBLECAPSBZMLNPYHJIAMYNKIZLGJLYRVYINRFXRYBQIQUIKNXNPYNAHNNYRNARRSVWHWIEXHUDLUOJKKILJBTEFUZNLLRPQPXZDDCFEKVTWMJBOZDPZQNQQMVPMGBCKTWPENZZHNUDFRNTKDCCMERVAUMAOIBKETWCOMPEHDPQMLCXQCJLLCEUSXKDJDATHWKNMSRJWELJPUANTLRUKOUKJOMCTEIHQHHNQJMANPZPUEVZKTNHYIKDLVYILYYNXTTVSDFVVRUYUCMRFWUTFHFHNKUCESXQHLPCXCYOQYCSFONTNMHSVLQRWKRCSGYRFSBPAUKANRDDYVNAENPWNFJVPIDGXBOVOZAKPMLOQFILSKNYPGURVKEERYYKFVDZCECVBXQHPAZWTMNOPUDPRMJMZXLLXISGIZHLPJUIJZKKFMYNYMXNVREZQJUNIQPFECPNEUEXPOAPSYURBGPGWNSOUWLVJFZTLPIXESLZYROIEZLBDCVICBEGVCLJSNCLWPMTWYTJQVCHQSRZSLZINRTNISVZEVJXQQIVXLAUYWQHQJFGBGSBWUWHFHCBDWLSZTNHOUMTLAAKZBJMSITVGOADFRXLSMIQUKQFPHTWTOPPDXQDBHTKMXXYUBIUVUBYNDMYOUBKKMYSAAAAAAAAAAAFSBYJKNCQHNTRSTUIQAAAAAAFTLTKQVAVITUKVDSNVDQJSAAAAAAAAAAAAAAAARPQAAAAAAAAAAAAAAAAAFSBYJKNCQHNTRSTUIQAAAAAAFTLTKQVAVITUKVDSNVDQJSAAAAATXTDNRUVUFHHDWIBRLSEDYIRTCJNLVOTSANJUSBUYQYPFESPOYXHHQXYUEVHYQRXPFEDVGYFQQVFMAHLDEZKVUTLVXMLAEQQQUHUDQDEBWHTLDNYZNLLEPLGOMSRVVSGYURXSLZYCOQQRFEBOJVYVMHEJTBEJMPMHLIGMGSIVQSSYGGNNVKZYPOUGSPMPNKYRJUZVDZMDVJIFKVYKUBJWWZFIDKSPRERHUREERFQQFVFQNNHAHXONNGVLVWSJLRUCMKZZJNNUXGRSZNBIQRRNFHEQFWSWDISQXTSFHLXGTOQXYRRASNSOYMJNPSNPVNOOBDZWPXJZLUYCSKZPVZBOCQFAQNDKBDDBULPRTOJKVQYMDUIIJUIGFODQFOYSYZFZLVURUXRXQYRRJALEXSOQQXDONZZBWQIUJEIYKNYBPUFWWPITYQKUFBHMZUWKZZVOHKMSOLPEDHSDOIUPYJYPMBMXZZJPBCJSHACKOYRCZLNGUYNNURMHYNCHYMPSYWCRMVFRCKKLVWXXOQBKJVJTFWBQAHRPVWOMAOTLQQWOAKPQXOBXTVRCLNTUDVVKJNRIILSIPZKIPUUFMGXHPXKWFQLEXQEWHCCSJPHAKZIPPGJMBLUQYLYVAQMVUVQZRSYNVGVCPANQUEIIYZFYRTNINVHZYIQFRFPQDKXATOOBTIKNKBHKQLEQYSRFVVVXBWXCDVFAQWKWZSSXDUKMEUBKDGTQWWJNSUFAERQJNIQIRLJERFXXOENBIFPZBYJLPUMJKSHFOIIKAZTKXZWIRMRJUVXKZQZLNLIXRCZIUOJZIRMTOJMWORWOMYUXZOINWYNUMLRPQURDOPFAMZRRFMIIIHZALVPLHMPTHBMYBFKATHONGLRKKNPEBZZMDOSZXORWORDXPROZNMREZCURWVXGAWMNURXAQRTNAVDDPAEYVXWRJFRVNZZOBVDKTZGOXKIMDQYMJBRQSWWWPOYPEKJIFARWIEPRZKWFIFEGQNILGMGSYRYISUKTRSOUSQNVOVUDCBFADCCJIIZXJALNXZJFIPLFLAALFOHZJWBLTGNRKFSYNKXKFUSFTCNPQTURETZUBLAQGRVKSOZEJSVDSZLEXZYLDOTPLJYPAERFRVPZMYHLXHLENZFOJNPVKRKDZOTZYPVOIHTOHNKRVZYPZSRXKNTJSVATDDLUOGJAUJRUIDNDYLCBQLXDYZMXWPSPWVYOQTKRDUVZOZENSTCOZESXUFZSZWHUESJNSVVYUEQIIUVVVXZNSDIAAAAABUTWOPJXEITERFEUFKLIEAAAAAABBDJEZPQRGLSLBPLBJVFWCBAAAAAADVFMKMOXWAFUTMRTDIVKMKCQNAXRUQVVEREQQOBGEVLMGJLSTSYKWWYSMJXDEVFBIXVAFRUTVUPHOVWGNTWVTAYOXJQDSPSRXMPZYAPVUURDPYSVSRZYSGYDLGIXPFTOWSJWMFWTTYGCTIIYCTOOQYZTGCORSDPNWANZVVEMSJZGWOZLVDZDMDEBBPDJUOULOTNDDSRTWMXSTUJGJVKJJYRHQIJSJXOVFBGTVVIOGZAEWUGLIMPMJHKOLYMYBCGPOXFSQYZIELJSOMFNKCMJQVGITHTKWNUSRNZNRWXQYAANWHXFNZTIYWLUGRNLDJPOMVTNIGRUKKTRZRKDXVSXNYIIRYOKIDQTOPNMAEJFVUVRMTZYKSYXDMONDIXIBRSIWIQIFKDDRRYGWDJCDVUIGENJKEFPFWCXGZLJYMHYKWHSOSLEFBLIKZIZXAGKJLBZHASBRWOYTNVZXWSSVLWFLYMKVQAEGSJQKOVNLLFPBWSNVRKNLYCKCRTPBOLSLWSWPYJCKSNIFVYRKXORKOQKNINVNJZQIIUPOWGSXNJAJKUFOWADRUUTWBAWSSUHXZAKVCQSVWVNQFUIOZZDKSAPWEZUQDXVGNMALWVZLVYIWZLEBKZJKKSCBZJJECQXFBYODZNRZDMFTRGCIILTBQLOJZWFTCUFBSVCRHISSXOAMKVIOWQVMEWSJHKIKILIXFVYBHRVAGRYNPZAKLIVZCVDRKNFUETQZVJMRJQTUPVEZIKFESDJYTTTPQHXNXWJJLLCJPTGCBPXCEHSNKPWFXXHSDLIIQXZDUVFAQKWTVFQZERPHKBNEABXVTFIFXEGTRNGVYFTDXJALINTESLERWLGOZKRQUMGURAPUNHKTXRUAGQCIUBNIAAAAABUTWOPJXEITERFEUFKLIEAAAAAABBDJEZPQRGLSLBPLBJVFWCBAAAAACSXLHPUUIYUDZIKDYGWTHBNNXVJURJHOAJNRQUAIQIIQPKYTFXRFVWEGRYTIJLAJKJJQSODSSFAOUYJDNFAGQSLPASLRYUHCLJMFBHMWDASXJSATRLEKRZXJHOIDURSJWGJIFLNOMROPFTSNTWRNQNKJSHWARMWMBFDJGOUJDCBJOUYYRLENMVMMYGMVICFUVSTEPBABNISOOBPVDLREDPJWPWWJFMTQTJYXNUSSECWOMCGENWPJEEBEZGIQUVDOBCUKNFJAZXGIZKSDTXGPVVVQLWLRJGTVSKVERFSZNLIYJEXLRSTNLVEYILSKXDTPMFWXMLHASDHIYMCVSJZVXUCLNIMQESXCWSDGEBWBQYXPKKSTQZJBDETETDJJPYHNYZIJZQQNTTYMUKZZWQNTCLAWHPBOIJUMNTOGWNUYOPWTYSTTNYJBGRNUTLVTRYOBOXTDVDAFACZGEOVMNHJMHMZSLYHKAXSZKUAXEEQKSBLGRUQJZONKRYTECXDHRTMDOFPQXLTBYBBTLDETQEVSUBKGIYRCKCSURQYNSNFBIVKZLZYPHHRQBIJQZOVUDRYXDVSMLNWTSDSAUETFZHVYZFNNTXVEPMVDTVZFUPQRYESPVTUWJZOYSRGUKSIKZFBBMTLEGFUKAXMSCHOUDSVJMFWXSQQZPKOTFLLDQCVZERFKVLMJRVQNRAVBSWJUUHUNLCQUCMPGCXKOHQYHFAGZDTDNZJPXRYRQULDLBVJMJKVALFDQOCYETGJMPAHKBYCYSGBZUSCLTZRUTBUPACTXDDKLIXHCZNZZLZVRMPFOORSVYKWNZVVJZSRMPZIRVVNLTGTGOJOLULSSYTDYOHSTQTWSYEPKAZRIBHWYOZVCYSEJYDQRWQPRYSQHDJXEKDPADRQASXNVCSLXUMMHQPCZLTVNZRSVAKWCKAJYXRUJEJVUQVLHABZOZWWMYWMQSRCHBTIQDKFSLWIHLLERWOVSODQPRLXZEKAFKUWLXXDKPKMQSMLKKZERVRVVNLUIIRXIUDFAUGBRUFZNWEUNPAVULABXLNJFPXSSFFLCDZROISRVRDIJIBUPRERVRKBEPFHEMOKTZQZBCPZLYOXQWCNNJGZSPAUVWWYVROYMQEQPDCJLZZRRUMIKWGUHMYKUMNUMLXBEAGTDNVTTGXBYPTHJMRBNTTXVCUYPDXVIGWEPBNUUWVVAWMFMXQLGRIKQTWARYZNLEIEVGDGSDBIFPXTUNLXEPNOFQYSZKIOKNYUBDGJMJLUEFEMKPXEWDHLSREMZQNYTLKCXHPAEWYVOYYMMPXRVWJQOOMRUEMZGRFNQDOJIPERPYYJSEVIPQXASOZUUYYYGNKJEIKIEYJURFBUCIPUSEPCZWLNIQYDJZYANCLIOTHJPNSPJVZOGODUVSOVGRUNIBVEOSWJOLOJOBVMCXWWRZOOYRULMSQZWTUVVKEWFLSVIMLTXWAAAAACPATRSTUIQMWOPJXEIAAAAAACSYFVXNKKWSXUTQHOEAAAAAGMUOTPDXFLUBXISUKGSDNXMUPFWZWJMOERHRVJJRHXQLDKNKGMSELAEMYOQCNUZDKWTIBDEVXXLKTXDIJJUYNVYTYRNCQDVANNYTPRBFUVYPSGQWQWZUXEPXWRRLNQXHJFUIUVVTLNIJWXZRSCRTZEDXRWLFULDPZMYOIMDVMXYKEYQAEOLRGHAZEFJRDAUAHYMVPBUSTLBYTPRUVAZXGBCVCQKZSYNCMRTQKAZRUEBKIRGXKJBUPQJDASFAWRLYELDCMPJVZCQKHKQOYZXTUOPKOLZRYEYHZXNWYYLTNCZSQDWVWCRLDXLYTUJRVRUVWRVTFCRLOHKETBQXSWEQTNYRVFKEVRUFSORUROZVSVRHERWCVSVWMVANRJCFKHOLDMHWRPIJMZZYRJMWOZIOJVBYRONZURAELYFRRBHAKYLMWRJJBUYVBJDKVZSRPEFJXRVHYQOZVQQQKQKHFRJVLJCFKKFXEHSVRFAOJZNKKMNPIRNYKQJVJGIIQUZEBDXKUCVEPEFQHMXPDXDKYJQDIATNHOYZZNJKBIXOTLWMBFZWURDUZRXNKNZCFQOLVAFXICJLXZLJLJTJESFUYDIMXZKJAIRMTHLSNVVXBZFREHECKWOTWEWYSNOIUGRMTDPARETRTAJZGUZOYAIZRYQILANMBGPKGSDIUQVLHXQEPYLZFGWRDNOJWURUVRXORMPRPMOVQKIJBMLSRSKTZJLMIMRWKKQNAQINFRETOIIHYSPOLIRRBAYNUUCOUQWUZLHFVRSNSXTQSPFAJOFPLHUWSFHIRZGUCZUPSCVWLWLDJLOZFVVFXAQZJDFWCSUBXXTKSRLGTCXUMVUCAWHEGDHZAXRQMLABBBJLYSTNDTFSJUTLVJEDQNOQOMQQFNFSEPZFUWVQUUHQRZVYOARKBKWKSACZWWSTWMDURLFYCPNKRPMHCNRSZHGYLTWOOCKJHVSJJLMLLZOWKZTCJVGMZNFIXZKEJQQOZURYMBRFVCTXKKHVOOWZFISSRWTWNUTRWQVSBNRYQITRVXYCXGNZABNKTXSXLLKOFBQRWUEKQXTRWOSIUREXTLNTMCDRURUEXOPOVPSDQEYAXYXGIVZUWBMTIQLDYTTNMOWUZXTTMYUVUFXGBGIOPRUCSDPPUWDLRYEFGKVLIZLBZOVAJTOKCGURFCUAONLEAMXXLTQTFNMJZKWNSSXEIPVSTSSKJVUKHSNSKVLUWDUSUNMKCFRLRYVDPJPDGIFTSRHBQZYQXTOTSGQXQJORVHRVXRURVFFRGYVVJVCKKRLSWEMXMNEYLIOTSYKTESWWKBGOQCRGVBSNIVLTUZVAWQYNOZEIFFZJETVOTPNJEYZXTNFISFZXQJUVLPAQSSCKBYRHURYKVRXXDSOOPUSTTHZNSJPWUHSQTZRDLSBDJUXPNGQKZMBWORUQOUNFKQFCWKNNHVGWBLACUNXOSORGOBOVXCQKJHNAQTROZGNVFANOPYAAAAAABUTWOPJXEITERFEUFKLIEAAAAAABBDJEZPQRGLSLBPLBJVFWCBAAAAADUPXZDDRZYSVGZRPREVZVRUXZENIIHSALGZIQKEXLFLMWOLRIMBBTGJZVVIKJQZWSRTZZUCEDCJHJZOXQUDHXAJHTTLUKDMJEVRDUWXWSRONHNGANFJUNXQQAALSTYMRZTNDZSROUHTAKOWZYLNSAJPPFTESRXPXVULLVEVWVTZRMOTPLMXKTIXNVMCVPURDFCIFETPTLUOXHZUIVGOIJTVTAELDZAJYWCPQLZGUQTVLGUMREFFKSZPCIOIBLSRNIVXDHDPXPSYVTTATEYKOLQTBYUCYVNOVRCANYUCQTVXLVVKTSNEJKVNZXEHRTVEDBLMYQRTRYJTVTPATFWEFLBASQTVLVYBEQSQSHZFJXTSOVKDVPLMXMAMRQIELTETLGLPZIXYLYDDERCZWMVGMIEJPJYFDUEDJBEIYKXUTOZUIUVTYRREAIONRBBAOXUMVZGVAIMVVGIFXASTBIHQPJUPWAWKCJEWJVSOQYQRZBRWRINORECJVDSOQTJLQTZRUEGZDUMNIKZGCNFCYUJZZOJYMMRNDKKEQCUYQDKHZFGJNTVYVFRVTKQQCPBKYQOERVINTNLTKCCYEVDRZHPFRVMEUDJJOACCJUWSTXSKAXJWVYKSABJBSIECHSUCWDUXRWQTSQYMHXKRWIYSXORRYUFKKFOSKJZHTFLVZVRYOYOUXVJPNZYKUVETZETGALEZIQYPTINFCYXGKARWOIYIKRTAXERUVQQIUHRIRUURUPHTAZHTHSQWVLISNMTHCZKMSDYBTLXPFHTZUUKCAOPAAAAAAAAKLQLHJPYHCBBYJKNCQGAAAAAALBPLBJVFWQRIYOTYFUTYARUBKVVRPJYCAQPEYKPRJVZCSHVWLCCOLNYQAVMOLZIFTXZATWKJSUFJDQYXPKHFMCAQPEYKPRJVZCSHVWLCCOLNYQAVMOLZIFTXZATWKJSUFJDQYXPKHFMCAQPEYKPRJVZCSHVWLCCOLNYQAVMOLZIFTXZATWKJSUFJDQYXPKHFMCAQPEYKPRJVZCSHVWLCCOLNYQAVMOLZIFTXZATWKJSUFJDQYXPKHFMCAQPEYKPRJVZCSHVWODWOPJXEIERIGTJZNLLRQORVLKBJTUQNLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADQ','E3Q02X2U','0');

/*Table structure for table `tbl_registration` */

DROP TABLE IF EXISTS `tbl_registration`;

CREATE TABLE `tbl_registration` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `tbl_registration` */

insert  into `tbl_registration`(`rid`,`name`,`phone`,`email`,`password`) values (3,'athul','9544351997','athul@gmail.com','123');

/*Table structure for table `text_file` */

DROP TABLE IF EXISTS `text_file`;

CREATE TABLE `text_file` (
  `fid` int(20) NOT NULL AUTO_INCREMENT,
  `uid` int(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `text` longblob,
  `key` varchar(30) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

/*Data for the table `text_file` */

insert  into `text_file`(`fid`,`uid`,`name`,`text`,`key`,`status`) values (21,3,'bank details','ACNO BALANCE','CBOZ6O7W','0'),(22,3,'personal','NAME ATHUL','IE9G72DQ','0'),(23,3,'name data','NAMEATHUL','VYMJ98NU','0'),(24,3,'daara','DATA12 HI 12','OY887NW2','0'),(25,3,'Hussain manalodi','1 H','HJPJ88X4','0'),(26,3,'test','22  123','N6VKCWD4','0');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
