document.write('<script src="js/echarts.min.js"></script>');
document.write('<script src="js/jquery-3.4.1.js"></script>');
let medicines1 = [
    "党参、丹参、桂枝、茯苓、桃仁、赤芍、丹皮、炒鳖甲、生牡蛎、炮穿山甲",
    "党参、丹参、桂枝、茯苓、桃仁、赤芍、丹皮、炒鳖甲、生牡蛎、炮穿山甲",
    "当归、桂枝、甘草、木通、肉苁蓉、白芍、细辛、桃仁、西洋参",
    "当归、桂枝、木通、白芍、细辛、西洋参、丹参、大枣、火麻仁、甘草",
    "熟地、肉苁蓉、菟丝子、杜仲、川牛膝、木瓜、炒鹿筋、当归、桃仁、红花、炮甲、火麻仁",
    "西洋参、炒白术、白芍、怀山药、柴胡、熟地、当归、杜仲、生枣仁、沙参、丹皮、肉苁蓉、火麻仁",
    "西洋参、炒白术、白芍、怀山药、柴胡、熟地、当归、杜仲、生枣仁、沙参、丹皮、肉苁蓉、火麻仁、黄芪、女贞子、淫羊藿、小海龙、广木香",
    "党参、吴茱萸、川芎、当归、酒白芍、丹皮、官桂、法半夏、麦冬、甘草、香附",
    "党参、吴茱萸、川芎、当归、酒白芍、丹皮、官桂、法半夏、麦冬、甘草、香附",
    "红参片、吴茱萸、川芎、当归、酒白芍、丹皮、官桂皮、法半夏、麦冬、甘草、香附、益母草",
    "刘寄奴、木瓜、琥珀、三棱、莪术、丹皮、官桂、延胡索、乌药、红藤、当归、酒白芍、田七片、五灵脂、炮穿山甲",
    "沙参、麦冬、白芍、当归、生地、枸杞子、川楝子、延胡索、琥珀、红藤、田七粉、生蒲黄、五灵脂",
    "西洋参、吴茱萸、川芎、当归、酒白芍、丹皮、官桂、法半夏、补骨脂、小茴香、延胡索、三七、甘草",
    "炒枣仁、茯神、知母、川芎、熟地、怀山药、山茱萸、枸杞子、当归、杜仲、川牛膝、炒龟板、延胡索、合欢花",
    "西洋参、吴茱萸、川芎、当归、酒白芍、丹皮、官桂、法半夏、补骨脂、小茴香、延胡索、三七、甘草",
    "黄芩、黄连、生大黄、生地、白芍、当归、川芎、水牛角片、丹皮、白茅根、甘草",
    "黄芩、黄连、生大黄、生地、白芍、当归、川芎、水牛角片、丹皮、白茅根、甘草",
    "黄芩、黄连、生大黄、生地、白芍、当归、川芎、水牛角片、丹皮、甘草",
    "桃仁、红花、生地、赤芍、当归、香附、莪术、广木香、川木通、炮甲、甘草",
    "生石膏、栀子、藿香、防风、桃仁、丹皮、当归、赤芍、生大黄、炮甲、连翘、甘草",
    "桃仁、红花、生地、赤芍、当归、川芎、炒枣仁、茯神、知母、生大黄、甘草",
    "熟地、菟丝子、白芍、当归、怀山药、茯苓、柴胡、香附",
    "西洋参、黄芪、白芍、葛根、蔓荆子、当归、川芎、生地、杜仲、续断、黄柏、甘草",
    "当归、白芍、熟地、川芎、阿胶、艾叶炭、党参、炒白术、甘草、怀山药、芡实、白果、车前子、黄柏",
    "当归、白芍、川芎、生地、黄芩、荆芥炭、地榆炭、黄柏、芡实、怀山药、白果、车前子、甘草、炒龟板",
    "桃仁、红花、熟地、赤芍、当归、川芎、香附、莪术、官桂、广木香、川木通、炮甲、甘草",
    "当归、白芍、柴胡、茯苓、炒白术、甘草、小海龙、炒鹿筋",
    "西洋参、炒白术、茯苓、当归、川芎、白芍、熟地、甘草、巴戟天、淫羊藿",
    "玄参、生地、麦冬、天冬、丹皮、地骨皮、白芍、阿胶、川芎、白芷、天麻、葛根、甘草",
    "玄参、生地、麦冬、天冬、丹皮、地骨皮、白芍、阿胶、甘草",
    "生地、白芍、当归、川芎、栀子炭、黄芩、阿胶、炒龟板、煅龙骨、甘草",
    "生地、白芍、当归、川芎、荆芥炭、黄芩、地榆炭、甘草",
    "当归、赤芍、丹皮、桃仁、酒大黄、红花、炮甲、甘草、连翘",
    "厚朴、陈皮、苍术、甘草、法半夏、炒菜菔子、炮甲、路路通、小海龙",
    "厚朴、陈皮、苍术、甘草、法半夏、炒菜菔子、炮甲、路路通、小海龙、山茱萸",
    "陈皮、法半夏、茯苓、甘草、砂仁、黄芩、炒白术",
    "熟地、白芍、当归、山茱萸、巴戟天、小海龙、炮甲",
    "熟地、白芍、当归、山茱萸、巴戟天、小海龙、肉桂",
    "熟地、白芍、当归、山茱萸、巴戟天、小海龙、炒鹿筋、桂枝、甘草、细辛、川木通",
    "黄芪、当归、西洋参、通草、王不留行、麦冬、炮甲",
    "黄芪、西洋参、当归、通草、王不留行、麦冬、炮甲、桔梗",
    "西洋参、当归、酒白芍、熟地、川芎、阿胶、艾叶炭、侧柏炭、地榆炭、甘草",
    "西洋参、当归、酒白芍、熟地、川芎、阿胶、艾叶炭、甘草",
    "西洋参、黄芪、独活、秦艽、防风、细辛、熟地、白芍、当归、川芎、桂枝、茯苓、杜仲、川牛膝、续断、甘草",
    "西洋参、黄芪、羌活、独活、秦艽、防风、细辛、熟地、白芍、当归、川芎、桂枝、茯苓、杜仲、川牛膝、续断、甘草",
    "西洋参、柴胡、黄芩、法半夏、大枣、甘草、黄芪、炒白术、防风、桑叶",
    "西洋参、柴胡、黄芩、法半夏、大枣、甘草、黄芪、炒白术、防风",
    "黄柏、怀山药、车前子、白果、芡实、蒲黄炭、五灵脂炭、生薏苡仁、败酱草、地榆炭、乌贼骨、蒲公英、田七",
    "黄柏、怀山药、车前子、白果、芡实、蒲黄炭、五灵脂炭、生薏苡仁、败酱草、地榆炭、乌贼骨、蒲公英、田七、白花蛇舌草",
    "黄柏、怀山药、车前子、白果、芡实、蒲黄炭、五灵脂炭、生薏苡仁、败酱草、鱼腥草、地榆炭、蒲公英、田七",
    "西洋参、茯苓、炒白术、甘草、巴戟天、炒薏苡仁、怀山药、煅龙骨、煅牡蛎、乌贼骨、肉桂",
    "西洋参、茯苓、炒白术、甘草、巴戟天、炒薏苡仁、煅龙骨、煅牡蛎、乌贼骨、肉桂",
    "西洋参、茯苓、炒白术、甘草、巴戟天、炒薏苡仁、煅龙骨、煅牡蛎、乌贼骨",
    "生地、当归、白芍、川芎、天葵子、鱼腥草、生薏苡仁、败酱草、黄柏、芡实、怀山药、白果、车前子、紫花地丁、蒲公英、银花、野菊花、甘草",
    "人工牛黄、黄柏、芡实、怀山药、白果、车前子、生薏苡仁、炒冬瓜子、败酱草、鱼腥草、延胡索、川楝子、红藤",
    "人工牛黄、黄柏、芡实、怀山药、白果、车前子、生薏苡仁、炒冬瓜子、败酱草、鱼腥草、延胡索、川楝子、红藤、熊胆粉",
    "西洋参、茯苓、炒白术、甘草、巴戟天、炒薏苡仁、苦参、黄柏",
    "西洋参、茯苓、炒白术、甘草、巴戟天、炒薏苡仁、苦参、黄柏、炒枣仁",
    "西洋参、炒白术、苍术、怀山药、车前子、当归、白芍、陈皮、柴胡、荆芥炭、芡实、薏苡仁、败酱草",
    "西洋参、炒白术、苍术、怀山药、车前子、当归、白芍、芡实、薏苡仁、败酱草、巴戟天、山茱萸",
    "西洋参、炒白术、巴戟天、茯苓、芡实、怀山药、车前子、香附、浙贝、木瓜、甘草",
    "当归、白芍、生地、荆芥、防风、甘草、刺蒺藜、白鲜皮、黄芩、苦参、银花、连翘、黄柏、青蒿、艾叶",
    "当归、白芍、生地、荆芥、防风、甘草、刺蒺藜、白鲜皮、黄芩",
    "砂仁、黄芩、陈皮、竹茹、法半夏、茯苓、甘草、苏梗",
    "砂仁、黄芩、陈皮、竹茹、法半夏、茯苓、甘草",
    "当归、白芍、川芎、熟地、阿胶、艾叶炭、黄芩、炒白术、甘草",
    "当归、白芍、川芎、熟地、阿胶、艾叶炭、黄芩、炒白术、甘草",
    "阿胶、艾叶炭、熟地、白芍、当归、川芎、荆芥炭、黄芩、甘草",
    "阿胶、艾叶炭、熟地、白芍、当归、川芎、黄芩、砂仁、炒白术、甘草",
    "熟地、白芍、当归、川芎、生大黄、火麻仁、葛根、羌活、炒瓜蒌",
    "熟地、白芍、当归、川芎、生大黄、火麻仁、葛根、羌活、炒瓜蒌",
    "熟地、生地、白芍、当归、川芎、火麻仁、甘草、杏仁、生大黄、柏子仁",
    "补骨脂、续断、桃仁、炮甲、小茴香、葫芦巴、延胡索、怀山药、茯苓、杜仲、川牛膝、炒鹿筋、菟丝子、甘草",
    "丹皮、栀子、当归、白芍、柴胡、茯苓、炒白术、甘草、青皮、枳壳、广木香、炮甲、杜仲、延胡索、炒枣仁",
    "丹皮、栀子、当归、白芍、柴胡、茯苓、炒白术、甘草、青皮、枳壳、广木香、炮甲、杜仲、延胡索、炒枣仁",
    "生大黄、陈皮、法半夏、茯苓、枳实、竹茹、甘草、桃仁、红花、生地、赤芍、当归、川芎",
    "酒大黄、陈皮、法半夏、茯苓、枳实、竹茹、甘草、苍耳子、辛夷、白芷、薄荷",
    "炒枣仁、知母、川芎、茯神、丹皮、栀仁、当归、白芍、炒白术、柴胡、甘草、炒龟板",
    "西洋参、黄芪、炒白术、陈皮、升麻、柴胡、当归、甘草、桑螵蛸、益智仁、菟丝子、覆盆子、白芍、木瓜",
    "西洋参、黄芪、炒白术、陈皮、升麻、柴胡、当归、甘草、白芍、熟地、荆芥炭、黄芩、侧柏炭、地榆炭",
    "西洋参、黄芪、炒白术、陈皮、升麻、柴胡、当归、甘草、桑螵蛸、益智仁、菟丝子、覆盆子、怀山药、白芍",
    "芡实、煅龙骨、煅牡蛎、五味子、白芍、怀山药、菟丝子、麦冬、知母、黄柏、炒龟板、生地、地骨皮、炒枣仁",
    "芡实、煅龙骨、煅牡蛎、五味子、白芍、怀山药、菟丝子、麦冬、知母、黄柏、炒龟板、生地、地骨皮、炒枣仁",
    "熟地、知母、黄柏、炒龟板、煅龙骨、煅牡蛎、柴胡、地骨皮、生地、炒鳖甲、青蒿、炒枣仁",
    "生地、白芍、当归、川芎、阿胶、艾叶炭、荆芥炭、黄芩、黄柏、怀山药、车前子、白果、芡实、甘草",
    "生地、白芍、当归、川芎、阿胶、艾叶炭、黄芩、黄柏、怀山药、车前子、白果、芡实、甘草、香附、浙贝、白花蛇舌草、鱼腥草",
    "当归、白芍、川芎、炒白术、茯苓、泽泻、黄柏、怀山药、车前子、白果、芡实、香附、浙贝、白花蛇舌草、鱼腥草、薏苡仁、煅乳香、煅没药、炒鳖甲、煅牡蛎、天葵子",
    "西洋参、丹参、阿胶、当归、白芍、熟地、川芎、甘草、艾叶炭、蒲黄炭",
    "西洋参、茯苓、炒白术、甘草、当归、白芍、熟地、川芎、荆芥炭、黄芩",
    "知母、黄柏、生地、怀山药、泽泻、丹皮、茯苓、炒龟板、人工牛黄、熊胆粉",
    "知母、黄柏、生地、怀山药、泽泻、丹皮、茯苓、炒龟板、人工牛黄、熊胆粉、甘草、萹蓄"
]

let medicines2 = [
    "麻黄、杏仁、生石膏、桑白皮、葶苈子、炙紫菀、百部、白前、陈皮、荆芥、川贝、桔梗、甘草",
    "炒瓜壳、桃仁、川贝、桑白皮、白前、法半夏、杏仁、桔梗、炙紫菀、百部、荆芥、薄荷、甘草、矮地茶",
    "桑叶、杏仁、沙参、麦冬、生石膏、阿胶、炙枇杷叶、甘草、桃仁、炒瓜壳、川贝、天花粉",
    "杏仁、桔梗、炙紫菀、百部、白前、陈皮、薄荷、矮地茶、甘草、桑白皮、川贝、青黛粉、海蛤粉",
    "麻黄、杏仁、炙冬花、法半夏、苏子、黄芩、白果、桑白皮、白芥子、炒菜菔子、甘草",
    "桑白皮、地骨皮、杏仁、炒瓜蒌壳、生大黄、生石膏、川贝、甘草",
    "麻黄、桂枝、白芍、细辛、五味子、干姜、法半夏、甘草",
    "芦根、桃仁、生薏苡仁、炒冬瓜子、黄连、法半夏、炒瓜壳、白及片、鱼腥草、野天麻、葛根、浙贝、防风",
    "百合、生地、熟地、玄参、浙贝、桔梗、麦冬、白芍、天冬、甘草、炙枇杷叶、白及片、丹皮、栀子炭、白茅根",
    "黄连、法半夏、炒瓜蒌壳、苇茎、薏苡仁、桃仁、椒目、葶苈、大枣、枳实",
    "香薷、厚朴、扁豆花、银花、连翘、黄芩、柴胡、法半夏、杏仁、甘草"
]

let medicines3 = [
    "麻黄、杏仁、生石膏、桑白皮、葶苈子、炙紫菀、百部、白前、陈皮、川贝、桔梗、甘草、法半夏",
    "黄芪、炒白术、防风、炒瓜壳、川贝、杏仁、桔梗、炙紫菀、百部、白前、陈皮、法半夏、甘草、矮地茶",
    "桑叶、杏仁、沙参、麦冬、生石膏、阿胶珠、炙枇杷叶、甘草、桃仁、花粉",
    "杏仁、桔梗、炙紫菀、百部、白前、陈皮、薄荷、矮地茶、甘草、桑白皮、川贝、青黛粉、海蛤粉",
    "麻黄、杏仁、炙冬花、法半夏、苏子、黄芩、白果、桑白皮、葶苈子、川贝、白芥子、炒菜菔子、厚朴、甘草",
    "麻黄、杏仁、甘草、苏子、桑白皮、川贝、生石膏、生大黄、炒瓜壳",
    "麻黄、射干、细辛、法半夏、炙紫菀、炙冬花、五味子、茯苓、大枣、生姜",
    "芦根、生薏苡仁、炒瓜壳、炒冬瓜子、杏仁、桔梗、炙紫菀、百部、陈皮、甘草、鱼腥草、浙贝、红藤",
    "百合、生地、熟地、玄参、浙贝、桔梗、麦冬、白芍、天冬、甘草、炙枇杷叶、白及片",
    "炒瓜壳、白芥子、川贝、椒目、茯苓、泽泻、猪苓、滑石、车前子、杏仁、枳实"
]
