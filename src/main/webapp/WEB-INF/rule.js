function format(data) {
	// var hq_str_sz002352="顺丰控股,53.500,50.190,55.210,55.210,52.000,55.210,0.000,24907542,1354013631.760,3988090,55.210,32500,55.200,12800,55.190,6300,55.180,2700,55.170,0,0.000,0,0.000,0,0.000,0,0.000,0,0.000,2017-02-24,15:28:03,00";
	var index = data.indexOf("=");
	data = data.substring(index + 1);
	var temp = eval(data).split(",");
	var result = {
		name: temp[0],
		todayStartPrice: temp[1],
		yesterdayEndPrice: temp[2],
		currentPrice: temp[3],
		todayHighestPrice: temp[4],
		todayLowwestPrice: temp[5]
	};
	return result;
}