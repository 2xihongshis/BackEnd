local urlKey = KEYS[1]
local url = ARGV[1]
redis.call("hincrby", urlKey, url, 1)

local sourceUrlKey = KEYS[2]
local sourceUrl = ARGV[2]
redis.call("hincrby", sourceUrlKey, sourceUrl, 1)

local regionCountryKey = KEYS[3]
local regionProvinceKey = KEYS[4]
local country = ARGV[3]
local province = ARGV[4]

local ipAllKey = KEYS[5]
local ipDayKey = KEYS[6]
local ip = ARGV[5];
redis.call("pfadd", ipAllKey, ip)
redis.call("pfadd", ipDayKey, ip)

local pvAllKey = KEYS[7]
local pvDayKey = KEYS[8]
local pvHourKey = KEYS[9]
redis.call("incr", pvAllKey)
redis.call("incr", pvDayKey)
redis.call("incr", pvHourKey)

local uuid = ARGV[6]
local uvAllKey = KEYS[10]
local uvDayNewKey = KEYS[11]
if redis.call("pfadd", uvAllKey, uuid) == 1 then
    redis.call("pfadd", uvDayNewKey, uuid)
    if country ~= "0" then
        redis.call("hincrby", regionCountryKey, country, 1)
    end
    if country == "中国" and province ~= "0" then
        redis.call("hincrby", regionProvinceKey, province, 1)
    end
end

local uvYesterdayKey = KEYS[12]
local uvYesterdayVisitsKey = KEYS[13]
local uvYesterdayTempKey = uvYesterdayKey .. "temp"
redis.call("pfmerge", uvYesterdayTempKey, uvYesterdayKey)
if redis.call("pfadd", uvYesterdayTempKey, uuid) ~= 1 then
    redis.call("pfadd", uvYesterdayVisitsKey, uuid)
end
redis.call("del", uvYesterdayTempKey)

local uvDayKey = KEYS[14]
local uvHourKey = KEYS[15]
redis.call("pfadd", uvDayKey, uuid)
redis.call("pfadd", uvHourKey, uuid)

return 1
