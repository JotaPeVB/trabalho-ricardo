import '../../global.css'
import { Button } from '../ui/button'
import { Search, PlusCircle } from 'lucide-react'
import { Input } from '../ui/input'
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '../ui/table'
import { Dialog } from '@radix-ui/react-dialog'
import { DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle, DialogTrigger } from '../ui/dialog'
import { Label } from '@radix-ui/react-label'

const AlunoTable = () => {
  return (
    <div className='p-6 max-w-4xl mx-auto space-y-4'>
        <h1 className='text-4xl font-bold'>Alunos</h1>

        <div className='flex itens-center justify-between'>
            <form className='flex items-center gap-2'>
            <Input name='Nome' placeholder='Nome do aluno'/>
            <Input name='Disciplina' placeholder='Disciplina'/>
            <Button type='submit' variant={'ghost'}>
                <Search  className='w-4 h-4 mr-2'/>
                Filtrar resultados
            </Button>
            </form>

            <Dialog>
                <DialogTrigger asChild>
                    <Button>
                        <PlusCircle  className='w-4 h-4 mr-2'/>
                        Novo Aluno
                    </Button>
                </DialogTrigger>

                <DialogContent>
                    <DialogHeader>
                        <DialogTitle>Novo Aluno</DialogTitle>
                        <DialogDescription>Adicione um novo aluno</DialogDescription>
                    </DialogHeader>

                    <form className='space-y-6' action=''>
                        <div className='grid grid-cols-4 items-center text-right gap-2 text-nowrap'>
                            <Label htmlFor='Nome'>Nome do aluno</Label>
                            <Input className='col-span-3' id='Nome'/>
                        </div>

                        <div className='grid grid-cols-4 items-center text-right gap-2 text-nowrap'>
                            <Label htmlFor='CPF'>CPF</Label>
                            <Input className='col-span-3' id='CPF'/>
                        </div>


                        <DialogFooter>
                            <Button type='submit'>Salvar</Button>
                        </DialogFooter>
                    </form>
                </DialogContent>
            </Dialog>
        </div>

        <div className='border-2 rounded-lg p-2'>
            <Table>
                <TableHeader>
                    <TableHead>Nome</TableHead>
                    <TableHead>CPF</TableHead>
                    <TableHead>Disciplinas</TableHead>
                </TableHeader>
                <TableBody>
                    {Array.from({ length: 10 }).map((_, i) => {
                        return (
                            <TableRow key={i}>
                                <TableCell>John Doe</TableCell>
                                <TableCell>123.456.789-11</TableCell>
                                <TableCell>2</TableCell>
                            </TableRow>
                        )
                    })}
                </TableBody>
            </Table>
        </div>
    </div>
  )
}

export default AlunoTable