#!/usr/bin/env python3

import asyncio
import os
from argparse import ArgumentParser
import aiohttp

url = None

async def push_apk(apkpath):
    print(f"[~] Pushing apk : {apkpath}")
    with open(apkpath, 'rb') as f:
        async with aiohttp.ClientSession() as session:
            await session.post(url + "/apk", data={'apk': f})

async def get_events():
  session = aiohttp.ClientSession()
  async with session.ws_connect(url + "/events") as ws:
    async for msg in ws:
      print('> ', msg.data)
      if msg.type in (aiohttp.WSMsgType.CLOSED,
        aiohttp.WSMsgType.ERROR):
        break
    await ws.close()
  await session.close()


async def amain(args):
  push_task = asyncio.create_task(push_apk(args.apkpath))
  evt_task = asyncio.create_task(get_events())

  await asyncio.gather(push_task)

  evt_task.kill()

if __name__ == '__main__':

  parser = ArgumentParser()
  parser.add_argument('apkpath', type=str, help='exploit apk path')
  parser.add_argument('--host', type=str, default='127.0.0.1', help='url host')
  parser.add_argument('--port', type=int, default=8080, help='url port')

  
  args = parser.parse_args()

  url = f"http://{args.host}:{args.port}"
  
  asyncio.run(amain(args))
  print(f"[+] Test finished")
